package se.steam.trellov2.repository.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public final class UserEntity extends AbstractEntity {

    private final String username, firstName, lastName;
    private final boolean active;
    @ManyToOne
    private final TeamEntity teamEntity;

    UserEntity() {
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.active = true;
        this.teamEntity = null;
    }

    public UserEntity(UUID id, String username, String firstName, String lastName, boolean active) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.teamEntity = null;
    }

    private UserEntity(UUID id, String username, String firstName, String lastName, boolean active, TeamEntity teamEntity) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.teamEntity = teamEntity;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isActive() {
        return active;
    }

    public UserEntity setTeamEntity(TeamEntity teamEntity) {
        return new UserEntity(getId(), username, firstName,
                lastName, active, teamEntity);
    }
}
