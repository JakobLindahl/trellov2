package se.steam.trellov2.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public final class Team extends AbstractModel<Team> {

    private final String name;
    private final boolean active;

    @JsonCreator
    public Team(String name) {
        super(null);
        this.name = name;
        this.active = true;
    }

    public Team(UUID id, String name, boolean active) {
        super(id);
        this.name = name;
        this.active = active;
    }


    @Override
    public Team assignId() {
        return new Team(UUID.randomUUID(), getName(), isActive());
    }

    public Team activateOrDeactivateTeam() {
        return new Team(getId(), getName(), !isActive());
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

}