package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.Team;
import se.steam.trellov2.model.User;
import se.steam.trellov2.repository.TeamRepository;
import se.steam.trellov2.repository.UserRepository;
import se.steam.trellov2.repository.model.TaskEntity;
import se.steam.trellov2.repository.model.TeamEntity;
import se.steam.trellov2.repository.model.UserEntity;
import se.steam.trellov2.repository.model.parse.ModelParser;
import se.steam.trellov2.service.TeamService;
import se.steam.trellov2.service.exception.DataNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
final class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public TeamServiceImp(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Team save(Team team) {
        return ModelParser.fromTeamEntity(teamRepository.save(ModelParser.toTeamEntity(team.assignId())));
    }

    @Override
    public Team get(UUID id) {
        return ModelParser.fromTeamEntity(validateTeam(id));
    }

    @Override
    public void update(Team team) {
        teamRepository.save(validateTeam(team.getId()));
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll()
                .stream()
                .map(ModelParser::fromTeamEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void addUserToTeam(UUID teamId, UUID userId) {
        userRepository.save(validateUser(userId).setTeamEntity(validateTeam(teamId)));
    }

    private TeamEntity validateTeam(UUID teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new DataNotFoundException("Couldn't find team with id [" + teamId + "]"));
    }

    private UserEntity validateUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Couldn't find User with id [" + userId + "]"));
    }

}