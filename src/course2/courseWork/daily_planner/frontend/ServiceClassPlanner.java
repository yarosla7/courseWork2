package course2.courseWork.daily_planner.frontend;

import course2.courseWork.daily_planner.backend.Planner;
import course2.courseWork.daily_planner.backend.exception.IncorrectArgumentException;
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
        if (tasksMap.containsKey(taskId) && !tasksMap.get(taskId).isDelete()) {
            tasksMap.get(taskId).setDelete(true);
            System.out.println("Задача успешно удалена и перенесена в архив.");
        } else {
            System.out.println("Задача успешно удалена.");
        }
        if (!tasksMap.containsKey(taskId)) {
            throw new TaskNotFoundException(taskId);
        }
    }

    public Collection<Planner> getAllDeletedTasks() {
        Collection<Planner> deletedTasks = new ArrayList<>();
        Collection<Planner> tasksValues = tasksMap.values();

        for (Planner task : tasksValues) {
            if (task.isDelete()) {
                deletedTasks.add(task);
            }
        }
        return deletedTasks;
    }

    public Collection<Planner> getAllTasksForDate(LocalDate date) {
        Collection<Planner> tasksForDate = new ArrayList<>();
        Collection<Planner> allTasks = tasksMap.values();

        for (Planner task : allTasks) {
            LocalDateTime currentDateTime = task.getTaskDataTime();

            if (currentDateTime.toLocalDate().equals(date)) {
                tasksForDate.add(task);
            }

            LocalDate tasksNextTime = LocalDate.from(currentDateTime);

            do {
                tasksNextTime = task.getTaskNextTime(tasksNextTime);

                if (tasksNextTime.equals(date)) {
                    tasksForDate.add(task);
                    break;
                }
            } while (tasksNextTime.isBefore(date));
        }
        return tasksForDate;
    }

    public void renameHeading(Integer taskId, String heading, String description) throws IncorrectArgumentException, TaskNotFoundException {
        if (tasksMap.containsKey(taskId)) {
            if (!heading.isEmpty()) {
                tasksMap.get(taskId).setHeading(heading);
                if (!description.isEmpty()) {
                    tasksMap.get(taskId).setDescription(description);
                }
                System.out.println("Задача успешно переименована!");
            }
        } else {
            throw new TaskNotFoundException(taskId);
        }
    }

    public void groupTasks() {
        Map<LocalDateTime, Planner> group = new HashMap<>();

        for (Map.Entry<Integer, Planner> task : tasksMap.entrySet()) {
            group.put(task.getValue().getTaskDataTime(), task.getValue());
        }

        for (Map.Entry<LocalDateTime, Planner> collect : group.entrySet()) {
            System.out.println("Задачи на дату " + collect.getKey().toLocalDate() + ": ");
            for (Planner task : tasksMap.values()) {
                if (collect.getKey().isEqual(task.getTaskDataTime())) {
                    System.out.println("\n" + task);
                }
            }
        }

    }


    public void getAllTasks() {
        System.out.println(tasksMap.values());
    }
}