package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.Team;
import se.steam.trellov2.repository.TeamRepository;
import se.steam.trellov2.repository.UserRepository;
import se.steam.trellov2.repository.model.TeamEntity;
import se.steam.trellov2.repository.model.UserEntity;
import se.steam.trellov2.repository.model.parse.ModelParser;
import se.steam.trellov2.service.TeamService;
import se.steam.trellov2.service.exception.DataNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static se.steam.trellov2.repository.model.parse.ModelParser.*;

@Service
final class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    private TeamServiceImp(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Team save(Team team) {
        return fromTeamEntity(teamRepository.save(toTeamEntity(team.assignId())));
    }

    @Override
    public Team get(UUID entityId) {
        return fromTeamEntity(validateTeam(entityId));
    }

    @Override
    public void update(Team entity) {
        validateTeam(entity.getId());
        teamRepository.save(toTeamEntity(entity));

    }

    @Override
    public void remove(UUID entityId) {
        teamRepository.save(validateTeam(entityId).deactivate());
    }

    @Override
    public void addUserToTeam(UUID teamId, UUID userId) {
        userRepository.save(validateUser(userId).setTeamEntity(validateTeam(teamId)));
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll()
                .stream()
                .map(ModelParser::fromTeamEntity)
                .collect(Collectors.toList());
    }

    private TeamEntity validateTeam(UUID entityId) {
        return teamRepository.findById(entityId)
                .orElseThrow(() -> new DataNotFoundException("Team with id [" + entityId + "]"));
    }
    private UserEntity validateUser(UUID entityId) {
        return userRepository.findById(entityId)
                .orElseThrow(() -> new DataNotFoundException("User with id [" + entityId + "]"));
    }

}