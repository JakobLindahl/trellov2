package se.steam.trellov2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.steam.trellov2.repository.model.TaskEntity;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
}
