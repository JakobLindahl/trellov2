package se.steam.trellov2.repository.data;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public final class User extends AbstractEntity{

    private final String username, firstName, lastName;
    private final boolean active;


    public User(UUID id, String username, String firstName, String lastName, boolean active) {
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
