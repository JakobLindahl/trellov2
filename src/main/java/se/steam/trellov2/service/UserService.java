package se.steam.trellov2.service;

import org.springframework.data.domain.Page;
import se.steam.trellov2.model.User;
import se.steam.trellov2.resource.parameter.PagingInput;
import se.steam.trellov2.resource.parameter.UserInput;

import java.util.List;
import java.util.UUID;

public interface UserService extends Service<User> {

    User save(User user);

    List<User> getByTeam(UUID teamId);

    List<User> getWithAttributes(UserInput userInput);

    void addTaskToUser(UUID userId, UUID taskId);

    Page<User> getPage(PagingInput pagingInput);

    void leaveTeam(UUID teamId, UUID userId);
}