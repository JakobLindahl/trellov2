package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.Issue;
import se.steam.trellov2.repository.IssueRepository;
import se.steam.trellov2.repository.TaskRepository;
import se.steam.trellov2.repository.model.IssueEntity;
import se.steam.trellov2.repository.model.parse.ModelParser;
import se.steam.trellov2.service.IssueService;
import se.steam.trellov2.service.exception.DataNotFoundException;

import java.util.UUID;

@Service
final class IssueServiceImp implements IssueService {

    private final IssueRepository issueRepository;
    private final TaskRepository taskRepository;

    private IssueServiceImp(IssueRepository issueRepository, TaskRepository taskRepository) {
        this.issueRepository = issueRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Issue save(UUID taskId, Issue issue) {
        return ModelParser.fromIssueEntity(issueRepository
                .save(ModelParser.toIssueEntity(issue.assignId())
                        .setTaskEnitity(taskRepository.findById(taskId)
                                .orElseThrow(() -> new DataNotFoundException("task not found")))));
    }

    @Override
    public void update(Issue entity) {
        issueRepository.save(issueRepository.findById(entity.getId())
                .map(issueEntity -> new IssueEntity(entity.getId(), entity.getDescription()))
                .orElseThrow(() -> new DataNotFoundException("Issue not found")));
    }

    @Override
    public void delete(UUID issueId) {
        issueRepository.delete(issueRepository.findById(issueId)
                .orElseThrow(() -> new DataNotFoundException("Issue not found")));
    }

}
