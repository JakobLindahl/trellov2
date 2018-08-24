package se.steam.trellov2.service.implementation;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import se.steam.trellov2.model.Issue;
import se.steam.trellov2.model.Task;
import se.steam.trellov2.model.Team;
import se.steam.trellov2.resource.parameter.PagingInput;
import se.steam.trellov2.service.IssueService;
import se.steam.trellov2.service.TaskService;
import se.steam.trellov2.service.TeamService;
import se.steam.trellov2.service.exception.DataNotFoundException;
import se.steam.trellov2.service.exception.InactiveEntityException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IssueServiceImpTest {

    @Autowired
    TeamService teamService;
    @Autowired
    TaskService taskService;
    @Autowired
    IssueService issueService;

    List<Team> teams;
    List<Task> tasks;
    List<Issue> issues;

    @Before
    public void setUp() throws Exception {
        teams = new ArrayList<>();
        tasks = new ArrayList<>();
        issues = new ArrayList<>();
        teams.add(teamService.save(new Team("TempTeam")));
        tasks.add((taskService.save(teams.get(0).getId(), new Task("hello", null))).getSecond());
    }
    @Test
    public void save() {
        //Remove task for failing test
        taskService.remove(tasks.get(0).getId());
        boolean thrown = false;
        try {
            Issue tempIssue = issueService.save(tasks.get(0).getId(), new Issue("Faling issue"));
        } catch (InactiveEntityException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    @After
    public void tearDown() throws Exception {

    }
}