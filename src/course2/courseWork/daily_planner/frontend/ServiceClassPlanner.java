package course2.courseWork.daily_planner.frontend;

import course2.courseWork.daily_planner.backend.Planner;
import course2.courseWork.daily_planner.backend.exception.TaskNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//класс для создания и управления задачами
public class ServiceClassPlanner {
    private final Map<Integer, Planner> tasksMap = new HashMap<>();

    public void addTask(Planner task) {
        this.tasksMap.put(task.getId(), task);
    }

    public void removeTask(Integer taskId) throws TaskNotFoundException {
        if (this.tasksMap.containsKey(taskId)) {
            this.tasksMap.remove(taskId);
        } else {
            throw new TaskNotFoundException(taskId);
        }
    }

    public Collection<Planner> getAllTasksForDate(LocalDate date) {
        Collection<Planner> tasksForDate = new ArrayList<>();
        Collection<Planner> allTasks = tasksMap.values();

        for (Planner task : allTasks) {
            LocalDateTime currentDateTime = task.getTaskDataTime();
            if (currentDateTime.toLocalDate().equals(date)) {
                tasksForDate.add(task);
            }

            LocalDateTime tasksNextTime = currentDateTime;

            do {
                tasksNextTime = task.getTaskNextTime(tasksNextTime);

                if (tasksNextTime.toLocalDate().equals(date)) {
                    tasksForDate.add(task);
                    break;
                }
            } while (tasksNextTime.toLocalDate().isBefore(date));
        }
        return tasksForDate;
    }

    public void getAllTasks() {
        System.out.println(tasksMap.values());
    }
}