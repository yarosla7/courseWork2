import course2.courseWork.daily_planner.backend.*;
import course2.courseWork.daily_planner.backend.exception.IncorrectArgumentException;
import course2.courseWork.daily_planner.backend.exception.TaskNotFoundException;
import course2.courseWork.daily_planner.frontend.ServiceClassPlanner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern DATA_TIME_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}"); //для корректного вывода даты и времени задачи
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final Pattern DATA_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final ServiceClassPlanner TASK_SERVICE = new ServiceClassPlanner();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.useDelimiter("\n");
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            printAllTasksForDate(scanner);
                            break;
                        case 4:
                            printAllTasks(scanner);
                            break;
                        case 5:
                            printAllDeleted(scanner);
                            break;
                        case 6:
                            renameHead(scanner, scanner, scanner);
                            break;
                        case 7:
                            printGroupedByDate(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        } catch (IncorrectArgumentException | TaskNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void inputTask(Scanner scanner) {

        scanner.useDelimiter("\n");

        String heading = inputTaskHeading(scanner);

        String description = inputTaskDescription(scanner);

        TaskType typo = inputTaskType(scanner);

        LocalDateTime taskTime = inputDataTime(scanner);

        int frequency = inputFrequency(scanner);

        createTask(heading, description, typo, taskTime, frequency);
    }

    private static void removeTask(Scanner scanner) {
        System.out.println("Введите ID задачи для удаления:");
        int id = scanner.nextInt();

        try {
            TASK_SERVICE.removeTask(id);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printAllTasksForDate(Scanner scanner) {
        System.out.println("Введите дату, в формате dd.MM.yyyy чтобы увидеть задачи на этот день:");

        if (scanner.hasNext(DATA_PATTERN)) {
            String dataTime = scanner.next(DATA_PATTERN);
            LocalDate inputDate = LocalDate.parse(dataTime, DATE_FORMATTER);
            Collection<Planner> taskByDate = TASK_SERVICE.getAllTasksForDate(inputDate);

            for (Planner task : taskByDate) {
                System.out.println(task);
            }
        } else {
            System.out.println("Всё норм? Введите дату в формате dd.MM.yyyy: ");
        }
    }

    private static void printAllTasks(Scanner scanner) {
        TASK_SERVICE.getAllTasks();
    }

    private static void printAllDeleted(Scanner scanner) {
        System.out.println(TASK_SERVICE.getAllDeletedTasks());
    }

    private static void renameHead(Scanner scanner, Scanner scanner2, Scanner scanner3) throws IncorrectArgumentException, TaskNotFoundException {
        System.out.println("Введите ID задачи: ");
        int id = scanner.nextInt();

        System.out.println("Введите название (оставьте поле пустым, чтобы ничего не менять): ");

        String heading = scanner2.next();

        System.out.println("Введите описание (оставьте поле пустым, чтобы ничего не менять): ");

        String description = scanner3.next();

        TASK_SERVICE.renameHeading(id, heading, description);
    }

    private static void printGroupedByDate(Scanner scanner) {
        System.out.println("Список задач сгруппированных по датам: ");
        TASK_SERVICE.groupTasks();
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу \n2. Удалить задачу \n3. Получить задачу на указанный день \n4. Получить все задачи \n5. Получить все удалённые задачи \n6.Переименовать название и/или описание задачи" +
                        "\n7. Сгруппировать задачи по датам \n0. Выход"
        );
    }

    //дополнительные методы: ===========================================================================================

    private static String inputTaskHeading(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String heading = scanner.next();

        if (heading.isBlank()) {
            System.out.println("Необходимо ввести название задачи");
        }
        return heading;
    }

    private static String inputTaskDescription(Scanner scanner) {
        System.out.print("Введите описание задачи: ");
        String description = scanner.next();

        if (description.isBlank()) {
            System.out.println("Необходимо ввести описание задачи");
        }
        return description;
    }

    private static TaskType inputTaskType(Scanner scanner) {

        System.out.println("Введите тип задачи (1 - личная, 2 - рабочая):");

        TaskType typo = null;

        int taskTypo = scanner.nextInt();

        switch (taskTypo) {
            case (1):
                typo = TaskType.PERSONAL;
                break;
            case (2):
                typo = TaskType.WORK;
                break;
            default: {
                System.out.println("Нужно выбрать корректной тип задачи");
            }
        }
        return typo;
    }

    private static LocalDateTime inputDataTime(Scanner scanner) {

        System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm : ");

        if (scanner.hasNext(DATA_TIME_PATTERN)) {
            String dataTime = scanner.next(DATA_TIME_PATTERN);
            return LocalDateTime.parse(dataTime, DATE_TIME_FORMATTER);
        } else {
            System.out.println("Всё норм? Введите дату и время задачи в формате dd.MM.yyyy HH:mm : ");
            return null;
        }
    }

    private static int inputFrequency(Scanner scanner) {
        System.out.println("Введите повторяемость задачи \n(1 - один раз;\n2 - каждый день;\n3 - каждую неделю;\n4- каждый месяц;\n5 - каждый год) : ");

        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            System.out.println("Введите число повторяемости задачи.");
        }
        return -1;
    }

    private static void createTask(String heading, String description, TaskType typo, LocalDateTime taskTime, int frequency) {
        Planner task = null;

        try {
            switch (frequency) {
                case 1:
                    task = new OneTime(heading, description, typo, taskTime);
                    break;
                case 2:
                    task = new Everyday(heading, description, typo, taskTime);
                    break;
                case 3:
                    task = new EveryWeek(heading, description, typo, taskTime);
                    break;
                case 4:
                    task = new EveryMonth(heading, description, typo, taskTime);
                    break;
                case 5:
                    task = new EveryYear(heading, description, typo, taskTime);
                    break;
                default: {
                    System.out.println("Повторяемость задачи введена некорректно.");
                }
            }
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }

        if (task != null) {
            TASK_SERVICE.addTask(task);
            System.out.println("Задача добавлена.");
        } else {
            System.out.println("Введены некорректные данные.");
        }
    }
}
//23.12.2023 14:00
//24.12.2023 14:00
//26.02.2023 12:00
//25.04.2023 15:00