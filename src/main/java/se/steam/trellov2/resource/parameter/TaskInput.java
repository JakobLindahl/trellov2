package se.steam.trellov2.resource.parameter;

import se.steam.trellov2.model.status.TaskStatus;

import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

public class TaskInput {

    private LocalDate startDate;

    private LocalDate endDate;

    @QueryParam("status")
    private TaskStatus status;

    @QueryParam("startDate")
    public void setStartDate(String startDate) {
        if (Optional.ofNullable(startDate).isPresent() &&
            Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$").matcher(startDate).matches()) {
            this.startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    @QueryParam("endDate")
    public void setEndDate(String endDate) {
        if (Optional.ofNullable(endDate).isPresent() &&
                Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$").matcher(endDate).matches()) {
                this.endDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

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
