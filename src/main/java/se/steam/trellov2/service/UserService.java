package se.steam.trellov2.service;

import se.steam.trellov2.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService extends Service<User> {

    List<User> getByTeam(UUID teamId);

}