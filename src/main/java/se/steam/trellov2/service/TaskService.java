package se.steam.trellov2.service;

import se.steam.trellov2.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService extends Service<Task> {

    List<Task> getByUser(UUID userId);

    List<Task> getByTeam(UUID teamID);

}