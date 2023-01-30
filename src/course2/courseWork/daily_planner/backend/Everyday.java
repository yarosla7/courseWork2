package course2.courseWork.daily_planner.backend;

import course2.courseWork.daily_planner.backend.exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

//задачи на каждый день
public class Everyday extends Planner {
    public Everyday(String heading, String description, TaskType taskType, LocalDateTime taskDataTime) throws IncorrectArgumentException {
        super(heading, description, taskType, taskDataTime);
    }

    @Override
    public LocalDate getTaskNextTime(LocalDate dateTime) {
        return dateTime.plusDays(1);
    }

    @Override
    public String toString() {
        return "<Ежедневная задача>\n " + super.toString();
    }

}
