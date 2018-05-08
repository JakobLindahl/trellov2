package se.steam.trellov2.model;

import java.util.UUID;

public abstract class AbstractDomainModel<T extends AbstractDomainModel<T>> {

    private final UUID id;

    AbstractDomainModel(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public abstract T assignId();
}
