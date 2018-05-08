package se.steam.trellov2.service;

import se.steam.trellov2.model.Team;
import se.steam.trellov2.model.User;

import java.util.List;
import java.util.UUID;

public final class TeamService implements TeamServiceInterface {
    @Override
    public Team saveTeam(Team team) {
        return null;
    }

    @Override
    public Team getTeamById(UUID id) {
        return null;
    }

    @Override
    public List<Team> getAllTeams() {
        return null;
    }

    @Override
    public void activateOrDeactivateTeam(Team team) {

    }

    @Override
    public void addUserToTeam(Team team, User user) {

    }
}
