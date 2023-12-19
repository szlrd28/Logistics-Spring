package newLog.newLog.service;

import newLog.newLog.model.Section;
import newLog.newLog.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {


    @Autowired
    SectionRepository sectionRepository;

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public Optional<Section> findByMilestoneId(Long milestoneId) {
        return sectionRepository.findByMilestoneId(milestoneId);
    }

    public Optional<Section> findBySectionNumber(Long transportplanId, Integer sectNumber){
        return sectionRepository.findBySectionNumber(transportplanId, sectNumber);
    }

    public Integer findMaxSectionById(Long transportplanId) {
        return sectionRepository.findMaxSectionById(transportplanId);
    }

    public Boolean checkThatMilestoneInSection(Long transportplanId, Long milestoneId) {
        List<Section> sectionsWithMilestones = sectionRepository.findByTransportAndMilestoneId(transportplanId, milestoneId);
        if(sectionsWithMilestones.isEmpty())
            return false;
        else
            return true;
    }

    @Transactional
    public void deleteAll() {
        getAllSections().stream().forEach(s -> s.setFromMilestone(null));
        getAllSections().stream().forEach(s -> s.setToMilestone(null));
        sectionRepository.deleteAll();
    }
}
