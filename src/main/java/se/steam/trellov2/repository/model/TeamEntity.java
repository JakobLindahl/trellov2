package se.steam.trellov2.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity(name = "Teams")
public final class TeamEntity  extends AbstractEntity{

    @Column(nullable = false)
    private final String name;
    private final boolean active;

    TeamEntity() {
        this.name = null;
        this.active = true;
    }

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
        return new TeamEntity(getId(), name, !isActive());
    }
}
