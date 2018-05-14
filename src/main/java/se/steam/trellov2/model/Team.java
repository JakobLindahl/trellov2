package se.steam.trellov2.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.UUID;

public final class Team extends AbstractModel<Team> {

    private final String name;

    @JsonCreator
    public Team(String name) {
        super(null);
        this.name = name;
    }

    public Team(UUID id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public Team assignId() {
        return new Team(UUID.randomUUID(), getName());
    }

    public String getName() {
        return name;
    }

}