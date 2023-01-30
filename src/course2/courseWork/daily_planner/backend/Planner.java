package course2.courseWork.daily_planner.backend;

import course2.courseWork.daily_planner.backend.exception.IncorrectArgumentException;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Planner implements Frequency {

    private static int idGenerator;
    private int id; //уникальный номер каждого таска
    private String heading;
    private String description;
    private TaskType taskType;
    private LocalDateTime taskDataTime;

    public Planner(String heading, String description, TaskType taskType, LocalDateTime taskDataTime) throws IncorrectArgumentException {
        setHeading(heading);
        setDescription(description);
        setTaskType(taskType);
        setTaskDataTime(taskDataTime);
        this.id = idGenerator++;
    }

//getters and setters: =============================================================================================

    public int getId() {
        return id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) throws IncorrectArgumentException {
        if (heading != null && !heading.isEmpty()) {
            this.heading = heading;
        } else {
            throw new IncorrectArgumentException("название задачи");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IncorrectArgumentException {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        } else {
            throw new IncorrectArgumentException("описание задачи");
        }
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) throws IncorrectArgumentException {
        if (taskType != null) {
            this.taskType = taskType;
        } else {
            throw new IncorrectArgumentException("тип задачи");
        }
    }

    public LocalDateTime getTaskDataTime() {
        return taskDataTime;
    }

    public void setTaskDataTime(LocalDateTime taskDataTime) throws IncorrectArgumentException {
        if (taskDataTime != null) {
            this.taskDataTime = taskDataTime;
        } else {
            throw new IncorrectArgumentException("тип задачи");
        }
    }

    //hashcode, equals & toString: =====================================================================================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planner planner = (Planner) o;
        return id == planner.id && Objects.equals(heading, planner.heading) && Objects.equals(description, planner.description) && taskType == planner.taskType && Objects.equals(taskDataTime, planner.taskDataTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heading, description, taskType, taskDataTime);
    }

    @Override
    public String toString() {
        return "Задача : " +
                "ID - " + id + "\nНазвание: " + heading + '\'' +
                "\nОписание: " + description + '\'' +
                "\nТип задачи: " + taskType +
                "\nДата и время исполнения задачи: " + taskDataTime + "\n";
    }
}
