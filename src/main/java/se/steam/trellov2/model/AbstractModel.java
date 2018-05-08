package se.steam.trellov2.model;

import java.util.UUID;

public abstract class AbstractModel<T extends AbstractModel<T>> {

    private final UUID id;

    AbstractModel(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public abstract T assignId();
}
