package se.steam.trellov2.model;

import java.util.UUID;

public final class User extends AbstractDomainModel<User> {

    private final String username, firstName, lastName;

    public User(UUID id, String username, String firstName, String lastName) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public User assignId() {
        return new User(UUID.randomUUID(),username,firstName,lastName);
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
}
