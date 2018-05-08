package se.steam.trellov2.repository.data;

import java.util.UUID;

public final class Task extends AbstractEntity {

    private final String description;

    protected Task() {
        description = null;
    }

    public Task(UUID id, String description) {
        super(id);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
