package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.User;
import se.steam.trellov2.repository.UserRepository;
import se.steam.trellov2.repository.model.parse.ModelParser;
import se.steam.trellov2.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
final class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private UserServiceImp(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User save(User entity) {
        return ModelParser.fromUserEntity(userRepository.save(ModelParser.toUserEntity(entity)));
    }

    @Override
    public User get(UUID entityId) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User toggleActive(UUID id) {
        return null;
    }

    @Override
    public List<User> getByTeam(UUID teamId) {
        return null;
    }
}
