package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.status.TaskStatus;
import se.steam.trellov2.repository.TaskRepository;
import se.steam.trellov2.service.TaskService;

import java.util.List;
import java.util.UUID;

@Service
final class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;

    private TaskServiceImp(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task entity) {
        return null;
    }

    @Override
    public Task get(UUID entityId) {
        return null;
    }

    @Override
    public void update(Task entity) {
    }

    @Override
    public void remove(UUID entityId) {

    }

    @Override
    public List<Task> getByUser(UUID userId) {
        return null;
    }

    @Override
    public List<Task> getByTeam(UUID teamID) {
        return null;
    }

    @Override
    public List<Task> getWithIssue() {
        return null;
    }

    @Override
    public List<Task> getByStatus(TaskStatus status) {
        return null;
    }

    @Override
    public List<Task> getByDescription(String description) {
        return null;
    }
}
