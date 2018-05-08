package se.steam.trellov2.service;

import se.steam.trellov2.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskServiceInterface {

    Task add(Task task);

    Task get(UUID id);

    Task update(Task task, UUID id);

    Task toggleActive(UUID id);

    List<Task> getByUser(UUID userId);

    List<Task> getByTeam(UUID teamID);

}