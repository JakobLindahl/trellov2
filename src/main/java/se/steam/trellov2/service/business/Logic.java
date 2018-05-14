package se.steam.trellov2.service.business;

import org.springframework.stereotype.Component;
import se.steam.trellov2.model.User;
import se.steam.trellov2.repository.TeamRepository;
import se.steam.trellov2.repository.UserRepository;
import se.steam.trellov2.repository.model.AbstractEntity;
import se.steam.trellov2.repository.model.TeamEntity;
import se.steam.trellov2.repository.model.UserEntity;
import se.steam.trellov2.service.exception.*;

import java.util.UUID;

@Component
public final class Logic {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public Logic(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }


    public TeamEntity checkTeamMaxCap(TeamEntity team) {
        if (userRepository.findByTeamEntity(team).size() >= 10)
            throw new TeamCapacityReachedException("Max users in team reached!");
        return team;
    }

    public UserEntity checkUserTeamAvailability(UserEntity user) {
        if (user.getTeamEntity() != null)
            throw new UserBelongingToTeamException("Cant add user to team! User already belongs to a team.");
        return user;
    }

    public User validateUsername(User user) {
        if (user.getUsername().length() < 10) {
            throw new WrongInputException("Username must contain at least 10 characters");
        }
        return user;
    }

    public

    private <T extends AbstractEntity> T checkIfActive(T entity) {
        if(entity.isActive()) {
            return entity;
        }
        throw new InactiveEntityException("Inactive " +
                entity.getClass().getSimpleName().replace("Entity", ""));
    }

    public TeamEntity validateTeam(UUID entityId) {
        return teamRepository.findById(entityId)
                .map(this::checkIfActive)
                .orElseThrow(() -> new DataNotFoundException("Team with id [" + entityId + "] not found"));
    }

    public UserEntity validateUser(UUID entityId) {
        return userRepository.findById(entityId)
                .map(this::checkIfActive)
                .orElseThrow(() -> new DataNotFoundException("User with id [" + entityId + "] not found"));
    }
}