package se.steam.trellov2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.repository.model.TaskEntity;
import se.steam.trellov2.repository.model.TeamEntity;
import se.steam.trellov2.repository.model.UserEntity;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    List<TaskEntity> findByUserEntity(UserEntity userEntity);

    List<TaskEntity> findByTeamEntity(TeamEntity teamEntity);

}
