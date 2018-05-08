package se.steam.trellov2.service;

public interface UserService {

    User get(UUID id);

    User add(User user);

    User update(User user, UUID id);

    User toggleActive(UUID id);

    List<User> getByTeam(UUID teamId);

}