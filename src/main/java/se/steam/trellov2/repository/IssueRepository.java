package se.steam.trellov2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.steam.trellov2.repository.model.IssueEntity;

import java.util.UUID;

public interface IssueRepository extends JpaRepository<IssueEntity, UUID> {
}
