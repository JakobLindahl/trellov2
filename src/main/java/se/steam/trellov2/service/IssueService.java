package se.steam.trellov2.service;

import org.springframework.data.domain.Page;
import se.steam.trellov2.model.Issue;
import se.steam.trellov2.resource.parameter.PagingInput;

import java.util.List;
import java.util.UUID;

public interface IssueService{

    Issue save(UUID taskId, Issue issue);

    void update(Issue issue);

    void delete(UUID IssueId);

    List<Issue> getPage(UUID teamId, PagingInput pagingInput);

}