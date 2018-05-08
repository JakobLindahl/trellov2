package se.steam.trellov2.repository.model;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public final class UserEntity extends AbstractEntity{

    private final String username, firstName, lastName;
    private final boolean active;

    UserEntity() {
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.active = true;
    }

    public UserEntity(UUID id, String username, String firstName, String lastName, boolean active) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
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
}
