package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.User;
import se.steam.trellov2.repository.TaskRepository;
import se.steam.trellov2.repository.TeamRepository;
import se.steam.trellov2.repository.UserRepository;
import se.steam.trellov2.repository.model.parse.ModelParser;
import se.steam.trellov2.resource.parameter.UserInput;
import se.steam.trellov2.service.UserService;
import se.steam.trellov2.service.exception.DataNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return ModelParser.fromUserEntity(userRepository.save(ModelParser.toUserEntity(entity.assignId())));
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
        userRepository.save(ModelParser.toUserEntity(entity));
    }

    @Override
    public void remove(UUID id) {
        userRepository.findById(id).map(x -> x.)
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
    public List<User> getWithAttributes(final UserInput userInput) {
        return userRepository.findAll().stream().filter((u) ->
                u.getFirstName() == null ? true : (u.getFirstName().contains(userInput.getFirstname()) &&
                u.getLastName() == null ? true : (u.getLastName().contains(userInput.getLastname()) &&
                u.getUsername() == null ? true : u.getUsername().contains(userInput.getUsername()))))
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