package se.steam.trellov2.repository.data;

import javax.persistence.Entity;
import java.util.List;
import java.util.UUID;

@Entity
public final class DUser extends AbstractEntity{

    private final String username, firstName, lastName;
    private final boolean active;


    public DUser(UUID id, String username, String firstName, String lastName, boolean active) {
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
