package se.steam.trellov2.model;

import java.util.UUID;

import static se.steam.trellov2.model.Status.*;

public final class Task extends AbstractDomainModel<Task> {

    private final String text;
    private final Status status;

    public Task(String text) {
        super(null);
        this.text = text;
        this.status = UNSTARTED;
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

    public Task setStatus(Status status) {
        return new Task(id, text, status);
    }
}