package se.steam.trellov2.service;

import se.steam.trellov2.model.Issue;

import java.util.UUID;

public interface IssueService{

    Issue save(UUID taskId, Issue issue);

    void update(Issue issue);

    void delete(UUID IssueId);
}
