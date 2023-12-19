package newLog.newLog.service;

import jakarta.transaction.Transactional;
import newLog.newLog.config.LogisticsConfigProperties;
import newLog.newLog.model.TransportPlan;
import newLog.newLog.repository.TransportPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IncomeService {

    @Autowired
    LogisticsConfigProperties config;

    @Autowired
    TransportPlanRepository transportPlanRepository;

    @Transactional
    public void adjustIncome(Long transportplanId, Integer delay) {
        TransportPlan transportPlan = transportPlanRepository.findById(transportplanId).get();
        Long currIncome = transportPlan.getIncome();
        Long newIncome = (currIncome * (100 - getIncomeDeclinePercentage(delay))) / 100;

        transportPlan.setIncome(newIncome);
    }

    private long getIncomeDeclinePercentage(int delay) {
        TreeMap<Integer, Integer> limits = config.getIncomeDeclinePercentage().getLimits();
        Map.Entry<Integer, Integer> floorEntry = limits.floorEntry(delay);

        return floorEntry == null ? 0 : floorEntry.getValue();
    }
}
