package se.steam.trellov2.repository.model;

public class TeamEntity {

    private final String name;
    private final boolean active;

    public TeamEntity(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

}
