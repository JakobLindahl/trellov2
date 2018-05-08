package se.steam.trellov2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.steam.trellov2.repository.model.TeamEntity;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamEntity, UUID> {
}
