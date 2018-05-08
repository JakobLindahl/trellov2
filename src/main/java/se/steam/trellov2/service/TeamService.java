package se.steam.trellov2.service;

import se.steam.trellov2.model.Team;
import se.steam.trellov2.model.User;

import java.util.UUID;

public interface TeamService extends Service<Team> {

    void addUserToTeam(Team team, User user);

}
