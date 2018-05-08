package se.steam.trellov2.repository.model;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public final class IssueEntity extends AbstractEntity {

    private final String description;

    public IssueEntity(UUID id, String description) {
        super(id);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
