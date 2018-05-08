package se.steam.trellov2.service;

import se.steam.trellov2.model.AbstractDomainModel;

import java.util.List;
import java.util.UUID;

public interface Service<T extends AbstractDomainModel> {

    T save(T entity);

    T get(UUID entityId);

    List<T> getAll();
}
