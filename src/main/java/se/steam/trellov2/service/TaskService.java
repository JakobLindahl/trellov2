package se.steam.trellov2.service;

import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.status.TaskStatus;
import se.steam.trellov2.resource.parameter.PagingInput;
import se.steam.trellov2.resource.parameter.TaskInput;

import java.util.List;
import java.util.UUID;

public interface TaskService extends Service<Task> {

    Task save(UUID teamId, Task entity);

    List<Task> getByUser(UUID userId);

    List<Task> getWithIssue();

    List<Task> getByStatus(TaskStatus status);

    List<Task> getByDescription(String description);

    List<Task> getByTeamAsPage(UUID teamId, PagingInput pagingInput, TaskInput taskInput);
}