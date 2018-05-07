package se.steam.trellov2.model;

import java.util.UUID;

public final class User extends AbstractDomainModel<User> {

    private final String username, firstName, lastName;
    private final boolean active;

    public User(UUID id, String username, String firstName, String lastName, boolean active) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

    @Override
    public User assignId() {
        return new User(UUID.randomUUID(),username,firstName,lastName, active);
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
