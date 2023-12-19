package newLog.newLog.service;

import newLog.newLog.model.TransportPlan;
import newLog.newLog.repository.TransportPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransportPlanService {


    @Autowired
    TransportPlanRepository transportplanRepository;

    @Autowired
    SectionService sectionService;

    public List<TransportPlan> getAllTransportPlans() {
        return transportplanRepository.findAll();
    }

    public Boolean checkExists(Long id) {
        return transportplanRepository.existsById(id);
    }

    public Optional<TransportPlan> findById(Long id){
        return transportplanRepository.findById(id);
    }

    @Transactional
    public void deleteAll() {
        sectionService.getAllSections().stream().forEach(s -> s.setTransportplan(null));
        getAllTransportPlans().stream().forEach(t -> t.setSections(null));
        transportplanRepository.deleteAll();
    }
}
