package se.steam.trellov2.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se.steam.trellov2.model.Issue;
import se.steam.trellov2.repository.IssueRepository;
import se.steam.trellov2.repository.TaskRepository;
import se.steam.trellov2.repository.model.parse.ModelParser;
import se.steam.trellov2.resource.parameter.PagingInput;
import se.steam.trellov2.service.IssueService;
import se.steam.trellov2.service.business.Logic;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
final class IssueServiceImp implements IssueService {

    private final IssueRepository issueRepository;
    private final Logic logic;

    private IssueServiceImp(IssueRepository issueRepository, Logic logic) {
        this.issueRepository = issueRepository;
        this.logic = logic;
    }

    @Override
    public Issue save(UUID taskId, Issue issue) {
        return ModelParser.fromIssueEntity(
                issueRepository.save(ModelParser.toIssueEntity(issue.assignId())
                        .setTaskEnitity(logic.checkIfDone(logic.validateTask(taskId)).dropTask())));

    }

    @Override
    public void update(Issue issue) {
        logic.validateIssue(issue.getId());
        issueRepository.save(ModelParser.toIssueEntity(issue));
    }

    @Override
    public void delete(UUID issueId) {
        issueRepository.delete(logic.validateIssue(issueId));
    }

    @Override
    public List<Issue> getPage(UUID teamId, PagingInput pagingInput) {
        return issueRepository.findAllByTeam(teamId, PageRequest.of(pagingInput.getPage(),pagingInput.getSize()))
                .stream()
                .map(ModelParser::fromIssueEntity)
                .collect(Collectors.toList());
    }

}