package se.steam.trellov2.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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
import se.steam.trellov2.resource.parameter.PagingInput;
import se.steam.trellov2.resource.parameter.TaskInput;
import se.steam.trellov2.service.TaskService;
import se.steam.trellov2.service.business.Logic;
import se.steam.trellov2.service.exception.DataNotFoundException;
import se.steam.trellov2.service.exception.WrongInputException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static se.steam.trellov2.repository.model.parse.ModelParser.fromTaskEntity;
import static se.steam.trellov2.repository.model.parse.ModelParser.toTaskEntity;

@Service
final class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final IssueRepository issueRepository;
    private final Logic logic;

    private TaskServiceImp(TaskRepository taskRepository, TeamRepository teamRepository, UserRepository userRepository, IssueRepository issueRepository, Logic logic) {
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.issueRepository = issueRepository;
        this.logic = logic;
    }

    @Override
    public Task save(UUID teamId, Task entity) {
        return fromTaskEntity(taskRepository.save(toTaskEntity(entity.assignId()).setTeamEntity(logic.validateTeam(teamId))));
    }

    @Override
    public Task get(UUID entityId) {
        return fromTaskEntity(logic.validateTask(entityId));
    }

    @Override
    public void update(Task entity) {
        logic.validateTask(entity.getId());
        taskRepository.save(toTaskEntity(entity));
    }

    @Override
    public void remove(UUID entityId) {
        taskRepository.save(logic.validateTask(entityId).deactivate());
    }

    @Override
    public List<Task> getByUser(UUID userId) {
        return taskRepository.findByUserEntity(logic.validateUser(userId))
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

    @Override
    public Page<Task> getByTeamAsPage(UUID teamId, PagingInput pagingInput, TaskInput taskInput) {
        return taskRepository.findByTeam(
                logic.validateTeam(teamId),
                taskInput.getStartDate(),
                taskInput.getEndDate(),
                taskInput.getStatus(),
                PageRequest.of(
                        pagingInput.getPage(),
                        pagingInput.getSize()))
                .map(ModelParser::fromTaskEntity);
    }

    @Override
    public void dropTask(UUID userId, UUID taskId) {
        TaskEntity t = logic.validateTask(taskId);
        if(t.getUserEntity().getId() == userId) {
            taskRepository.save(t.dropTask());
        } else {
            throw new WrongInputException("Task does not belong to User");
        }
    }
}