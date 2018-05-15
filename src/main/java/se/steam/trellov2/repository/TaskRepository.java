package se.steam.trellov2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.steam.trellov2.model.status.TaskStatus;
import se.steam.trellov2.repository.model.TaskEntity;
import se.steam.trellov2.repository.model.TeamEntity;
import se.steam.trellov2.repository.model.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    List<TaskEntity> findByUserEntity(UserEntity userEntity);

    Page<TaskEntity> findByTeamEntity(TeamEntity teamEntity, Pageable pageable);

    @Query("select t from Tasks t join t.teamEntity e where e.id = ?1 and (?2 is null or t.date >= ?2) and (?3 is null or t.date <= ?3) and (?4 is null or t.status = ?4)")
    Page<TaskEntity> findByTeam(UUID id, LocalDate startDate, LocalDate endDate, TaskStatus status, Pageable pageable);
}
