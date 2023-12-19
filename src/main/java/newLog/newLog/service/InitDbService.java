package newLog.newLog.service;

import newLog.newLog.model.Address;
import newLog.newLog.model.Milestone;
import newLog.newLog.model.Section;
import newLog.newLog.model.TransportPlan;
import newLog.newLog.repository.AddressRepository;
import newLog.newLog.repository.MilestoneRepository;
import newLog.newLog.repository.SectionRepository;
import newLog.newLog.repository.TransportPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class InitDbService {
    /*@Autowired
    AddressService addressService;*/

    @Autowired
    MilestoneService milestoneService;

   @Autowired
    SectionService sectionService;
  @Autowired
    TransportPlanService transportPlanService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    MilestoneRepository milestoneRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    TransportPlanRepository transportplanRepository;


    public void clearDb() {
        addressRepository.deleteAllInBatch();

    }

    @Transactional
    public void initDb() {
        Address address1 = new Address(null,"CH", "Zürich", "Gemeinde str.", "8000", "13", 47.3, 8.54);
        Address address2 = new Address(null, "CH", "Bern", "Kramgasse", "3011", "4", 46.94797, 7.44744);
        Address address3 = new Address(null, "CH", "Geneva", "Rue du Rhône", "1204", "22", 46.20439, 6.14316);
        Address address4 = new  Address(null, "CH", "Lucerne", "Pilatusstrasse", "6003", "7", 47.05017, 8.30931);
        Address address5 = new Address(null, "CH", "Basel", "Steinentorstrasse", "4051", "33", 47.55960, 7.58061);
        Address address6 = new Address(null, "CH", "Lausanne", "Rue de Bourg", "1003", "12", 46.51965, 6.63227);
        Address address7 = new Address(null, "CH", "St. Gallen", "Multergasse", "9000", "9", 47.42448, 9.37671);
        Address address8 = new Address(null,"CH", "Lugano", "Via Nassa", "6900", "15", 46.00368, 8.95105);

        addressRepository.save(address1);
        addressRepository.save(address2);
        addressRepository.save(address3);
        addressRepository.save(address4);
        addressRepository.save(address5);
        addressRepository.save(address6);
        addressRepository.save(address7);
        addressRepository.save(address8);


        Milestone m1 = milestoneRepository.save(new Milestone(LocalDateTime.now(), address1));
        Milestone m2 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusHours(3), address2));
        Milestone m3 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusHours(4), address2));
        Milestone m4 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusHours(8), address3));
        Milestone m5 = milestoneRepository.save(new Milestone(LocalDateTime.now().plusHours(1), address1));

        Section s1 = sectionRepository.save(new Section(1, m1, m2));
        Section s2 = sectionRepository.save(new Section(2, m3, m4));

        TransportPlan t1 = transportplanRepository.save(new TransportPlan());
        t1.addSection(s1);
        t1.addSection(s2);
        t1.setIncome(1000L);

    }

    @Transactional
    public void deleteAllTables() {
        transportPlanService.deleteAll();
        sectionService.deleteAll();
        milestoneService.deleteAll();
        //addressService.deleteA;
    }


}
