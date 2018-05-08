package se.steam.trellov2.service.implementation;

import org.springframework.stereotype.Service;
import se.steam.trellov2.model.Issue;
import se.steam.trellov2.repository.IssueRepository;
import se.steam.trellov2.service.IssueService;

import java.util.List;
import java.util.UUID;

@Service
final class IssueServiceImp implements IssueService {

    private final IssueRepository issueRepository;

    private IssueServiceImp(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public Issue save(Issue entity) {
        return null;
    }

    @Override
    public Issue get(UUID entityId) {
        return null;
    }

    @Override
    public Issue update(Issue entity) {
        return null;
    }

    @Override
    public List<Issue> getAll() {
        return null;
    }
}
