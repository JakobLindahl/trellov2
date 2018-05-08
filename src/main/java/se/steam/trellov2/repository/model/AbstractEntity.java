package se.steam.trellov2.repository.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Column(columnDefinition = "binary(16)")
    private final UUID id;

    AbstractEntity() {
        this.id = null;
    }

    AbstractEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
