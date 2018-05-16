package se.steam.trellov2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se.steam.trellov2.repository.model.TeamEntity;
import se.steam.trellov2.repository.model.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    List<UserEntity> findByTeamEntity(TeamEntity teamEntity);

    Page<UserEntity> findByFirstNameContainingAndLastNameContainingAndUsernameContaining(String firstName, String lastName, String username, Pageable pageable);
}
