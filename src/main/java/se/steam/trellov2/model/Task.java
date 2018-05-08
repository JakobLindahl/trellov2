package se.steam.trellov2.model;

import se.steam.trellov2.model.status.TaskStatus;

import java.util.UUID;

import static se.steam.trellov2.model.status.TaskStatus.*;

public final class Task extends AbstractModel<Task> {

    private final String text;
    private final TaskStatus status;

    public Task(String text) {
        super(null);
        this.text = text;
        this.status = UNSTARTED;
    }

    public Task(UUID id, String text, TaskStatus status) {
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

    public TaskStatus getStatus() {
        return status;
    }

    public Task setStatus(TaskStatus status) {
        return new Task(getId(), text, status);
    }
}