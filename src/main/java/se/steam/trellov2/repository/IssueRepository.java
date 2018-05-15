package se.steam.trellov2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se.steam.trellov2.repository.model.IssueEntity;
import se.steam.trellov2.repository.model.TeamEntity;

import java.util.List;
import java.util.UUID;

public interface IssueRepository extends JpaRepository<IssueEntity, UUID> {

    Page<IssueEntity> findByTaskEntityTeamEntity(TeamEntity teamEntity, Pageable pageable);

}
