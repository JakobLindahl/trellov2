package se.steam.trellov2.service.businesslogic;

import se.steam.trellov2.repository.UserRepository;
import se.steam.trellov2.repository.model.TeamEntity;
import se.steam.trellov2.repository.model.UserEntity;
import se.steam.trellov2.service.exception.TeamCapacityReachedException;
import se.steam.trellov2.service.exception.UserBelongingToTeamException;

public final class BusinessLogic {

    private BusinessLogic() {
    }

    public static TeamEntity checkTeamMaxCap(UserRepository repository, TeamEntity team) {
        if (repository.findByTeamEntity(team).size() >= 10)
            throw new TeamCapacityReachedException("Max users in team reached!");
        return team;
    }

    public static UserEntity checkUserTeamAvailability(UserEntity user) {
        if (user.getTeamEntity() != null)
            throw new UserBelongingToTeamException("Cant add user to team! User already belongs to a team.");
        return user;
    }

}
