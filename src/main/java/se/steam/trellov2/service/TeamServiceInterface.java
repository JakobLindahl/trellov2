package se.steam.trellov2.service;

import se.steam.trellov2.model.Team;
import se.steam.trellov2.model.User;

import java.util.UUID;

public interface TeamServiceInterface {

    Team saveTeam(Team team);

    Team getTeamById(UUID id);

    Iterable<Team> getAllTeams();

    void activateOrDeactivateTeam(Team team);

    void addUserToTeam(Team team, User user);

}
