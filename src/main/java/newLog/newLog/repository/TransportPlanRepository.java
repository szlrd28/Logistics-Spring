package newLog.newLog.repository;


import newLog.newLog.model.TransportPlan;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long> {

    @EntityGraph("TransportPlan-full")
    @Query("SELECT t FROM TransportPlan t WHERE t.id=:id")
    Optional<TransportPlan> findById(Long id);

}
