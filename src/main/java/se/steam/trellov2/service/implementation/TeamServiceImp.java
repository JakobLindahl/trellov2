package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.Team;
import se.steam.trellov2.model.User;
import se.steam.trellov2.repository.TeamRepository;
import se.steam.trellov2.service.TeamService;

import java.util.List;
import java.util.UUID;

@Service
final class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;

    private TeamServiceImp(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team save(Team entity) {
        return null;
    }

    @Override
    public Team get(UUID entityId) {
        return null;
    }

    @Override
    public List<Team> getAll() {
        return null;
    }

    @Override
    public void activateOrDeactivateTeam(Team team) {

    }

    @Override
    public void addUserToTeam(Team team, User user) {

    }

}
