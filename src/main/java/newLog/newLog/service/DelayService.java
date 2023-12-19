package newLog.newLog.service;

import jakarta.transaction.Transactional;
import newLog.newLog.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DelayService {


    @Autowired
    SectionService sectionService;

    @Autowired
    MilestoneService milestoneService;

    @Transactional
    public void adjustMilestone(Long transportplanId, Long milestoneId, Integer delay) {
        Long fromId = null;
        Long toId = null;
        Integer maxSectNumber = sectionService.findMaxSectionById(transportplanId);
        Section section = sectionService.findByMilestoneId(milestoneId).get();

        if(section.getFromMilestone().getId() == milestoneId) {
            fromId = milestoneId;
            toId = section.getToMilestone().getId();
        }

        if(section.getToMilestone().getId() == milestoneId) {
            if(section.getSectNumber() < maxSectNumber) {
                Integer nextSectNumber = section.getSectNumber() + 1;
                Section nextSection = sectionService.findBySectionNumber(transportplanId, nextSectNumber).get();
                toId = milestoneId;
                fromId = nextSection.getFromMilestone().getId();
            } else {
                toId = milestoneId;
                fromId = null;
            }
        }

        if(fromId != null)
            milestoneService.incrementPlannedTime(fromId, delay);
        if(toId != null)
            milestoneService.incrementPlannedTime(toId, delay);
    }
}
