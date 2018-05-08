package se.steam.trellov2.model;

import java.util.UUID;

public final class Task extends AbstractDomainModel<Task> {

    private final String text;
    private Status status;

    public Task(String text, Status status) {
        super(null);
        this.text = text;
        this.status = status;
    }

    public Task(UUID id, String text, Status status) {
        super(id);
        this.text = text;
        this.status = status;
    }

    @Override
    public Task assignId() {
        return new Task(UUID.randomUUID(), text, status);
    }

    public String getText() {
        return text;
    }

    public Status getStatus() {
        return status;
    }

}
