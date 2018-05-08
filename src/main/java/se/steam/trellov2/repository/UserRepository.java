package se.steam.trellov2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.steam.trellov2.repository.model.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
