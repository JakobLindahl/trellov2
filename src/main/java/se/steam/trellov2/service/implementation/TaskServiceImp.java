package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.repository.TaskRepository;
import se.steam.trellov2.repository.TeamRepository;
import se.steam.trellov2.repository.UserRepository;
import se.steam.trellov2.repository.model.TaskEntity;
import se.steam.trellov2.repository.model.TeamEntity;
import se.steam.trellov2.repository.model.UserEntity;
import se.steam.trellov2.repository.model.parse.ModelParser;
import se.steam.trellov2.resource.TeamResource;
import se.steam.trellov2.service.TaskService;
import se.steam.trellov2.service.exception.DataNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
final class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;

    private TaskServiceImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task entity) {
        return ModelParser.fromTaskEntity(taskRepository.save(ModelParser.toTaskEntity(entity.assignId())));
    }

    @Override
    public Task get(UUID entityId) {
        return ModelParser.fromTaskEntity(validateTask(entityId));
    }

    @Override
    public void update(Task entity) {
        validateTask(entity.getId());
        taskRepository.save(ModelParser.toTaskEntity(entity));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(ModelParser::fromTaskEntity)
                .collect(Collectors.toList());
    }

    private TaskEntity validateTask(UUID entityId) {
        return taskRepository.findById(entityId)
                .orElseThrow(() -> new DataNotFoundException("Couldn't find task with id [" + entityId + "]"));
    }

}
