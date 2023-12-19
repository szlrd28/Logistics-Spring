package newLog.newLog.repository;

import newLog.newLog.model.Section;
import newLog.newLog.model.TransportPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {

    @Query("SELECT s FROM Section s "
            + "WHERE s.fromMilestone.id = :milestoneId OR s.toMilestone.id = :milestoneId")
    Optional<Section> findByMilestoneId(Long milestoneId);

    @Query("SELECT s FROM Section s "
            + "WHERE s.transportplan.id = :transportplanId "
            + "AND s.sectNumber = :sectNumber")
    Optional<Section> findBySectionNumber(Long transportplanId, Integer sectNumber);

    @Query("SELECT max(s.sectNumber) FROM Section s "
            + "WHERE s.transportplan.id = :transportplanId")
    Integer findMaxSectionById(Long transportplanId);

    @Query("SELECT s FROM Section s "
            + "WHERE s.transportplan.id = :transportplanId "
            + "AND (s.fromMilestone.id = :milestoneId OR s.toMilestone.id = :milestoneId)")
    List<Section> findByTransportAndMilestoneId(Long transportplanId, Long milestoneId);
}
