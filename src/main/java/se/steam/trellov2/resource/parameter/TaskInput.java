package se.steam.trellov2.resource.parameter;

import se.steam.trellov2.model.status.TaskStatus;

import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.util.Optional;

public class TaskInput {

    @QueryParam("startDate")
    private LocalDate startDate;

    @QueryParam("endDate")
    private LocalDate endDate;

    @QueryParam("status")
    private TaskStatus status;

    public Optional<LocalDate> getStartDate() {
        return Optional.ofNullable(startDate);
    }

    public Optional<LocalDate> getEndDate() {
        return Optional.ofNullable(endDate);
    }

    public Optional<TaskStatus> getStatus() {
        return Optional.ofNullable(status);
    }
}
