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

@Service
final class TeamServiceImp implements TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    private TeamServiceImp(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Team save(Team entity) {
        return ModelParser.fromTeamEntity(teamRepository.save(ModelParser.toTeamEntity(entity.assignId())));
    }

    @Override
    public Team get(UUID entityId) {
        return ModelParser.fromTeamEntity(validate(entityId));
    }

    @Override
    public void update(Team entity) {
        validate(entity.getId());
        teamRepository.save(ModelParser.toTeamEntity(entity));

    }

    @Override
    public void remove(UUID entityId) {
        teamRepository.delete(validate(entityId));

    }

    @Override
    public void addUserToTeam(UUID teamId, UUID userId) {
        userRepository.save(validateUser(userId).setTeamEntity(validate(teamId)));
    }

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll()
                .stream()
                .map(ModelParser::fromTeamEntity)
                .collect(Collectors.toList());
    }

    private TeamEntity validate(UUID entityId) {
        return teamRepository.findById(entityId)
                .orElseThrow(() -> new DataNotFoundException("Team with id [" + entityId + "]"));
    }
    private UserEntity validateUser(UUID entityId) {
        return userRepository.findById(entityId)
                .orElseThrow(() -> new DataNotFoundException("User with id [" + entityId + "]"));
    }

}