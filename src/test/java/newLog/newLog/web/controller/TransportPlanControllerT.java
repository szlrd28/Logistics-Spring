package newLog.newLog.web.controller;

import newLog.newLog.config.LogisticsConfigProperties;
import newLog.newLog.dto.DelayDto;
import newLog.newLog.model.Milestone;
import newLog.newLog.model.TransportPlan;
import newLog.newLog.repository.AddressRepository;
import newLog.newLog.repository.MilestoneRepository;
import newLog.newLog.repository.SectionRepository;
import newLog.newLog.repository.TransportPlanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TransportPlanControllerT {

    private static final String BASE_URI = "/api/transportPlans";

    @Autowired
    WebTestClient webTestClient;


    @Test
    public void testThatTransportplanIdDoesNotFound() throws Exception {
        DelayDto delayDto = new DelayDto(1L, 60);
        webTestClient.post()
                .uri(String.format("%s/%s/delay", BASE_URI, 2L))
                .bodyValue(delayDto).exchange().expectStatus()
                .isNotFound();
    }

}