package se.steam.trellov2.repository.model;

import se.steam.trellov2.repository.data.AbstractEntity;

import java.util.UUID;

public final class TeamEntity  extends AbstractEntity{

    private final String name;
    private final boolean active;

    public TeamEntity(UUID id, String name, boolean active) {
        super(id);
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public TeamEntity activateOrDeactivateTeam() {
        return new TeamEntity(getId(), getName(), !isActive());
    }
}
