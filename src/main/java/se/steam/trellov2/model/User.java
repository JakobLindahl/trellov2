package se.steam.trellov2.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public final class User extends AbstractModel<User> {

    private final String username, firstName, lastName;
    private final boolean active;

    @JsonCreator
    public User(UUID id, String username, String firstName, String lastName) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = true;
    }

    public User(UUID id, String username, String firstName, String lastName, boolean active) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

    @Override
    public User assignId() {
        return new User(UUID.randomUUID(), username, firstName, lastName);
    }

    public User activateOrDeactivateUser() {
        return new User(getId(), username, firstName, lastName, !isActive());
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
