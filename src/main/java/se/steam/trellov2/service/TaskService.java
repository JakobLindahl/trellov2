package se.steam.trellov2.service;

import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.status.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    Task save(UUID teamId,Task entity);

    Task get(UUID entityId);

    void update(Task entity);

    void remove(UUID entityId);

    List<Task> getByUser(UUID userId);

    List<Task> getByTeam(UUID teamID);

    List<Task> getWithIssue();

    List<Task> getByStatus(TaskStatus status);

    List<Task> getByDescription(String description);

}