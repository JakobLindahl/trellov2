package se.steam.trellov2.service;

import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.User;
import se.steam.trellov2.resource.parameter.UserInput;

import java.util.List;
import java.util.UUID;

public interface UserService extends Service<User> {

    List<Task> getAllTasksByUser(UUID userId);

    List<User> getWithAttributes(UserInput userInput);

    void addTaskToUser(UUID userId, UUID taskId);
}