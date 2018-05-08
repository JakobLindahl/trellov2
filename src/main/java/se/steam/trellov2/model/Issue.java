package se.steam.trellov2.model;

import java.util.UUID;

public final class Issue extends AbstractModel<Issue> {

    private final String description;

    public Issue(UUID id, String description) {
        super(id);
        this.description = description;
    }

    @Override
    public Issue assignId() {
        return new Issue(UUID.randomUUID(), getDescription());
    }

    public String getDescription() {
        return description;
    }
}
