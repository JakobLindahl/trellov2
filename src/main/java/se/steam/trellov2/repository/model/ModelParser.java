package se.steam.trellov2.repository.model;

import se.steam.trellov2.model.Issue;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.Team;
import se.steam.trellov2.model.User;

public class ModelParser {

    private ModelParser() {
    }

    public static User fromUserEntity(UserEntity u) {
        return new User(u.getId(), u.getUsername(), u.getFirstName(),
                u.getLastName(), u.isActive());
    }

    public static UserEntity toUserEntity(User u) {
        return new UserEntity(u.getId(), u.getUsername(),
                u.getFirstName(), u.getLastName(), u.isActive());
    }

    public static Task fromTaskEntity(TaskEntity t) {
        return new Task(t.getId(), t.getText(), t.getStatus());
    }

    public static TaskEntity toTaskEntity(Task t, TeamEntity team) {
        return new TaskEntity(t.getId(),t.getText(),t.getStatus(), team);
    }

    public static Team fromTeamEntity(TeamEntity t) {
        return new Team(t.getId(), t.getName(), t.isActive());
    }

    public static TeamEntity toTeamEntity(Team t) {
        return new TeamEntity(t.getId(), t.getName(), t.isActive());
    }

    public static Issue fromIssueEntity(IssueEntity i) {
        return new Issue(i.getId(), i.getDescription());
    }

    public static IssueEntity toIssueEntity(Issue i) {
        return new IssueEntity(i.getId(), i.getDescription());
    }
}