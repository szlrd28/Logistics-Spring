package newLog.newLog.service;

import newLog.newLog.model.Milestone;
import newLog.newLog.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MilestoneService {


    @Autowired
    MilestoneRepository milestoneRepository;

    public List<Milestone> getAllMilestones() {
        return milestoneRepository.findAll();
    }

    public Boolean checkExists(Long id) {
        return milestoneRepository.existsById(id);
    }

    public void incrementPlannedTime(Long milestoneId, Integer delay) {
        Milestone milestone = milestoneRepository.findById(milestoneId).get();
        milestone.setPlannedTime(milestone.getPlannedTime().plusMinutes(delay));
    }

    public List<Milestone> findByAddressId(long id) {
        return milestoneRepository.findByAddressId(id);
    }

    @Transactional
    public void deleteAll() {
        getAllMilestones().stream().forEach(m -> m.setAddress(null));
        milestoneRepository.deleteAll();
    }
}
