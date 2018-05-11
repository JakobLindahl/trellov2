package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.User;
import se.steam.trellov2.repository.TaskRepository;
import se.steam.trellov2.repository.TeamRepository;
import se.steam.trellov2.repository.UserRepository;
import se.steam.trellov2.repository.model.UserEntity;
import se.steam.trellov2.repository.model.parse.ModelParser;
import se.steam.trellov2.resource.parameter.UserInput;
import se.steam.trellov2.service.UserService;
import se.steam.trellov2.service.exception.DataNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static se.steam.trellov2.repository.model.parse.ModelParser.fromUserEntity;
import static se.steam.trellov2.repository.model.parse.ModelParser.toUserEntity;

@Service
final class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TaskRepository taskRepository;

    private UserServiceImp(UserRepository userRepository, TeamRepository teamRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public User save(User entity) {
        return fromUserEntity(userRepository.save(toUserEntity(entity.assignId())));
    }

    @Override
    public User get(UUID entityId) {
        return userRepository.findById(entityId)
                .map(ModelParser::fromUserEntity)
                .orElseThrow(() -> new DataNotFoundException("User not found"));
    }

    @Override
    public void update(User entity) {
        userRepository.findById(entity.getId()).orElseThrow(() -> new DataNotFoundException("User not found"));
        userRepository.save(toUserEntity(entity));
    }

    @Override
    public void remove(UUID id) {
        userRepository.save(userRepository.findById(id)
                .map(UserEntity::deactivate)
                .orElseThrow(() -> new DataNotFoundException("User not found")));
    }

    @Override
    public List<User> getByTeam(UUID teamId) {
        return userRepository.findByTeamEntity(teamRepository.findById(teamId)
                .orElseThrow(() -> new DataNotFoundException("Team not found")))
                .stream()
                .map(ModelParser::fromUserEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getWithAttributes(UserInput userInput) {
        return userRepository.findAll().stream().filter((u) ->
                    u.isActive() &&
                    (u.getFirstName() == null || (u.getFirstName().contains(userInput.getFirstname())) &&
                    (u.getLastName() == null || (u.getLastName().contains(userInput.getLastname())) &&
                    (u.getUsername() == null || u.getUsername().contains(userInput.getUsername())))))
                .map(ModelParser::fromUserEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void addTaskToUser(UUID userId, UUID taskId) {
        taskRepository.save(taskRepository.findById(taskId)
                .orElseThrow(RuntimeException::new)
                .setUserEntity(userRepository.findById(userId)
                        .orElseThrow(RuntimeException::new))
        );
    }
}