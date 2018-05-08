package se.steam.trellov2.repository.model;

import se.steam.trellov2.model.status.TaskStatus;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public final class TaskEntity extends AbstractEntity {

    private final String text;
    @Enumerated
    private final TaskStatus status;
    @ManyToOne
    @JoinColumn(name = "userEntity")
    private final UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "teamEntity", nullable = false)
    private final TeamEntity teamEntity;

    TaskEntity(){
        this.text = null;
        this.status = null;
        this.userEntity = null;
        this.teamEntity = null;
    }

    public TaskEntity(UUID id, String text, TaskStatus status) {
        super(id);
        this.text = text;
        this.status = status;
        this.userEntity = null;
        this.teamEntity = null;
    }

    private TaskEntity(UUID id, String text, TaskStatus status, UserEntity userEntity, TeamEntity teamEntity) {
        super(id);
        this.text = text;
        this.status = status;
        this.userEntity = userEntity;
        this.teamEntity = teamEntity;
    }

    public String getText() {
        return text;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public TaskEntity setUserEntity(UserEntity userEntity) {
        return new TaskEntity(getId(), text, status, userEntity, teamEntity);
    }

    public TaskEntity setTeamEntity(TeamEntity teamEntity) {
        return new TaskEntity(getId(), text, status, userEntity, teamEntity);
    }
}