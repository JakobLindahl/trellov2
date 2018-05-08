package se.steam.trellov2.repository.model;

import se.steam.trellov2.model.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public final class TaskEntity extends AbstractEntity {

    private final String text;
    private final Status status;
    @ManyToOne
    private final UserEntity userEntity;
    @ManyToOne
    @Column (nullable = false)
    private final TeamEntity teamEntity;

    protected TaskEntity(){
        this.text = null;
        this.status = null;
        this.userEntity = null;
        this.teamEntity = null;
    }

    public TaskEntity(UUID id, String text, Status status, TeamEntity teamEntity) {
        super(id);
        this.text = text;
        this.status = status;
        this.userEntity = null;
        this.teamEntity = teamEntity;
    }

    public TaskEntity(UUID id, String text, Status status, UserEntity userEntity, TeamEntity teamEntity) {
        super(id);
        this.text = text;
        this.status = status;
        this.userEntity = userEntity;
        this.teamEntity = teamEntity;
    }

    public String getText() {
        return text;
    }

    public Status getStatus() {
        return status;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public TaskEntity setUserEntity(UserEntity userEntity) {
        return new TaskEntity(id, text, status, userEntity, teamEntity);
    }
}