package course2.courseWork.daily_planner.backend;

import course2.courseWork.daily_planner.backend.exception.IncorrectArgumentException;

import java.time.LocalDate;
import java.time.LocalDateTime;

//задачи 1 раз в месяц
public class EveryMonth extends Planner {
    public EveryMonth(String heading, String description, TaskType taskType, LocalDateTime taskDataTime) throws IncorrectArgumentException {
        super(heading, description, taskType, taskDataTime);
    }

    @Override
    public LocalDate getTaskNextTime(LocalDate dateTime) {
        return dateTime.plusMonths(1);
    }

    @Override
    public String toString() {
        return "<Ежемесячная задача>\n " + super.toString();
    }
}
