package se.steam.trellov2.service;

import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.Team;
import se.steam.trellov2.model.User;

import java.util.List;
import java.util.UUID;

public interface TeamService extends Service<Team> {

    void addUserToTeam(UUID teamId, UUID userId);

    void addTaskToTeam(UUID teamId, UUID taskId);

    List<User> getAllUsersInTeam(UUID teamId);

    List<Task> getAllTasksInTeam(UUID teamId);

}
