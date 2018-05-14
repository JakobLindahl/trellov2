package se.steam.trellov2.resource.parameter;

import se.steam.trellov2.model.status.TaskStatus;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;

public class PagingInput {

    @QueryParam("startDate")
    @DefaultValue("currentMonth")
    private LocalDate startDate;

    @QueryParam("endDate")
    @DefaultValue("today")
    private LocalDate endDate;

    @QueryParam("status")
    @DefaultValue("Done")
    private TaskStatus status;

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
