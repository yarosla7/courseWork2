package course2.courseWork.daily_planner.backend;

//одноразовая задача

import course2.courseWork.daily_planner.backend.exception.IncorrectArgumentException;

import java.time.LocalDateTime;

public class OneTime extends Planner {
    public OneTime(String heading, String description, TaskType taskType, LocalDateTime taskDataTime) throws IncorrectArgumentException {
        super(heading, description, taskType, taskDataTime);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime dateTime) {
        return null;
    }
}
