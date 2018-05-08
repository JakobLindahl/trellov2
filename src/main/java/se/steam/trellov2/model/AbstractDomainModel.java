package se.steam.trellov2.model;

import java.util.UUID;

abstract class AbstractDomainModel<T extends AbstractDomainModel<T>> {

    protected final UUID id;

    AbstractDomainModel(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public abstract T assignId();
}
