package course2.courseWork.daily_planner.backend.exception;
//ошибка если задачи не существует по ключу
public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(Integer taskId) {
        super("Задача с ID: " + taskId + " не найдена.");
    }
}
