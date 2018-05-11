package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.Issue;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.status.TaskStatus;
import se.steam.trellov2.repository.IssueRepository;
import se.steam.trellov2.repository.TaskRepository;
import se.steam.trellov2.repository.TeamRepository;
import se.steam.trellov2.repository.UserRepository;
import se.steam.trellov2.repository.model.IssueEntity;
import se.steam.trellov2.repository.model.TaskEntity;
import se.steam.trellov2.repository.model.TeamEntity;
import se.steam.trellov2.repository.model.UserEntity;
import se.steam.trellov2.repository.model.parse.ModelParser;
import se.steam.trellov2.service.TaskService;
import se.steam.trellov2.service.exception.DataNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static se.steam.trellov2.repository.model.parse.ModelParser.*;

@Service
final class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final IssueRepository issueRepository;

    private TaskServiceImp(TaskRepository taskRepository, TeamRepository teamRepository, UserRepository userRepository, IssueRepository issueRepository) {
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.issueRepository = issueRepository;
    }

    @Override
    public Task save(UUID teamId, Task entity) {
        return fromTaskEntity(taskRepository.save(toTaskEntity(entity.assignId()).setTeamEntity(validateTeam(teamId))));
    }

    @Override
    public Task get(UUID entityId) {
        return fromTaskEntity(validateTask(entityId));
    }

    @Override
    public void update(Task entity) {
        validateTask(entity.getId());
        taskRepository.save(toTaskEntity(entity));
    }

    @Override
    public void remove(UUID entityId) {
        taskRepository.save(validateTask(entityId).deactivate());
    }

    @Override
    public List<Task> getByUser(UUID userId) {
        return taskRepository.findByUserEntity(validateUser(userId))
                .stream()
                .map(ModelParser::fromTaskEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getByTeam(UUID teamID) {
        return taskRepository.findByTeamEntity(validateTeam(teamID))
                .stream()
                .map(ModelParser::fromTaskEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getWithIssue() {
        return issueRepository.findAll().stream().map(IssueEntity::getTaskEntity).map(ModelParser::fromTaskEntity).collect(Collectors.toList());
    }

    @Override
    public List<Task> getByStatus(TaskStatus status) {
        return taskRepository.findAll()
                .stream()
                .filter(t -> status.equals(t.getStatus()))
                .map(ModelParser::fromTaskEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getByDescription(String description) {
        return taskRepository.findAll()
                .stream()
                .filter(t -> t.getText().contains(description))
                .map(ModelParser::fromTaskEntity)
                .collect(Collectors.toList());
    }

    private TeamEntity validateTeam(UUID entityId) {
        return teamRepository.findById(entityId)
                .orElseThrow(() -> new DataNotFoundException("Team with id [" + entityId + "]"));
    }

    private UserEntity validateUser(UUID entityId) {
        return userRepository.findById(entityId)
                .orElseThrow(() -> new DataNotFoundException("User with id [" + entityId + "]"));
    }

    private TaskEntity validateTask(UUID entityId) {
        return taskRepository.findById(entityId)
                .orElseThrow(() -> new DataNotFoundException("Task with id [" + entityId + "]"));
    }

}
