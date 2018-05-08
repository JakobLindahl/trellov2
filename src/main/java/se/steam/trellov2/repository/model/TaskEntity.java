package se.steam.trellov2.repository.model;

import se.steam.trellov2.model.Status;
import se.steam.trellov2.model.Task;

import java.util.UUID;

public final class TaskEntity extends AbstractEntity {

    private final String text;
    private final Status status;

    protected TaskEntity(){
        this.text = null;
        this.status = null;
    }

    public TaskEntity(UUID id, String text, Status status) {
        super(id);
        this.text = text;
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public Status getStatus() {
        return status;
    }
}
