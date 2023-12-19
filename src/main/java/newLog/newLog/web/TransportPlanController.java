package newLog.newLog.web;


import newLog.newLog.dto.DelayDto;
import newLog.newLog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportPlanController {
    @Autowired
    TransportPlanService transportplanService;

    @Autowired
    SectionService sectionService;

    @Autowired
    MilestoneService milestoneService;

    @Autowired
    DelayService delayService;

    @Autowired
    IncomeService incomeService;

    @PostMapping("/{id}/delay")
    public void registerDelay(@PathVariable Long id, @RequestBody DelayDto delayDto) {
        if(transportplanService.checkExists(id) && milestoneService.checkExists(delayDto.getId())) {
            if(sectionService.checkThatMilestoneInSection(id, delayDto.getId())) {
                delayService.adjustMilestone(id, delayDto.getId(), delayDto.getDelay());
                incomeService.adjustIncome(id, delayDto.getDelay());
            }
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


    }
