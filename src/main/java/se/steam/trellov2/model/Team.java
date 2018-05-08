package se.steam.trellov2.model;

import java.util.UUID;

public final class Team extends AbstractModel<Team> {

    private final String name;
    private final boolean active;

    protected Team(){
        super(null);
        name=null;
        active=true;
    }

    public Team(UUID id, String name, boolean active) {
        super(id);
        this.name = name;
        this.active = active;
    }

    public Team(UUID id, String name) {
        super(id);
        this.name = name;
        this.active = true;
    }

    @Override
    public Team assignId() {
        return new Team(UUID.randomUUID(), getName());
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
