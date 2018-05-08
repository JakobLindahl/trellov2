package se.steam.trellov2.service;

import se.steam.trellov2.model.Team;

import java.util.UUID;

public interface TeamService extends Service<Team> {

    void addUserToTeam(UUID teamId, UUID userId);

}
