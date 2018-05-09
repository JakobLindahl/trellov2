package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.User;
import se.steam.trellov2.repository.TeamRepository;
import se.steam.trellov2.repository.UserRepository;
import se.steam.trellov2.repository.model.parse.ModelParser;
import se.steam.trellov2.service.UserService;
import se.steam.trellov2.service.exception.DataNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
final class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;

    private UserServiceImp(UserRepository userRepository, TeamRepository teamRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
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
    public List<User> getAll() {
        return userRepository.findAll()
                .stream()
                .map(ModelParser::fromUserEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksByUser(UUID userId) {
        return null;
    }
}