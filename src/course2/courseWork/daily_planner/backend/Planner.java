package course2.courseWork.daily_planner.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Planner implements Frequency {
    private static int id; //уникальный номер каждого таска
    private final String heading;
    private final String description;
    private final LocalDateTime dataTime;
    public static List<Planner> list = new ArrayList<>();
    private final TypesOfRepetitions howOftenRemind;
    public static Map<Integer, Planner> listOfTasks = new HashMap<>(); //для создания мапы со всеми ключами и объектами
    private LocalDate date = LocalDate.now();//по умолчанию дата будет текущая и от нее отсчет на повторения
    private boolean isWorkTask; //проверка на тип задачи

    public Planner(String heading, String description, String howOftenRemind) {
        if (heading == null || heading.isEmpty() || heading.isBlank()) {
            throw new IllegalArgumentException("Не указан заголовок задачи!");
        } else {
            this.heading = heading;
        }
        if (description == null || description.isEmpty() || description.isBlank()) {
            throw new IllegalArgumentException("Не указано описание задачи!");
        } else {
            this.description = description;
        }
        this.howOftenRemind = TypesOfRepetitions.findByString(howOftenRemind); // здесь нет проверки так как у меня указан enum и невозможно ввести некорректное значение и у класса enum есть свои исключения.
        this.dataTime = LocalDateTime.now();
        this.date = LocalDate.now();
        this.isWorkTask = false;
        ++id;
        listOfTasks.put(getId(), this); // создание таблицы ключей и значений сразу на моменте создания объектов (задач)
    }

    public Planner(String heading, String description, String howOftenRemind, boolean isWorkTask) {
        if (heading == null || heading.isEmpty() || heading.isBlank()) {
            throw new IllegalArgumentException("Не указан заголовок задачи!");
        } else {
            this.heading = heading;
        }
        if (description == null || description.isEmpty() || description.isBlank()) {
            throw new IllegalArgumentException("Не указано описание задачи!");
        } else {
            this.description = description;
        }
        this.howOftenRemind = TypesOfRepetitions.findByString(howOftenRemind); // здесь нет проверки так как у меня указан enum и невозможно ввести некорректное значение и у класса enum есть свои исключения.
        this.dataTime = LocalDateTime.now();
        this.date = LocalDate.now();
        this.isWorkTask = isWorkTask;
        ++id;
        listOfTasks.put(getId(), this); // создание таблицы ключей и значений сразу на моменте создания объектов (задач)
    } //конструктор, где прописывается тип задачи (параметры первого конструктора не перенесены, так как почему-то идея требовала сделать dataTime не final

    public Planner(String heading, String description, TypesOfRepetitions howOftenRemind) {
        if (heading == null || heading.isEmpty() || heading.isBlank()) {
            throw new IllegalArgumentException("Не указан заголовок задачи!");
        } else {
            this.heading = heading;
        }
        if (description == null || description.isEmpty() || description.isBlank()) {
            throw new IllegalArgumentException("Не указано описание задачи!");
        } else {
            this.description = description;
        }
        this.howOftenRemind = howOftenRemind;
        this.dataTime = LocalDateTime.now();
        switch (howOftenRemind) {
            case EVERYDAY:
                this.date = LocalDate.now();
                break;
            case ONE_TIME:
                this.date = dataTime.toLocalDate();
            case EVERY_WEEK:
                if (this.date.isEqual(date)) {
                    this.date.plusWeeks(1);
                }
                break;
            case EVERY_MONTH:
                if (this.date.isEqual(date)) {
                    this.date.plusMonths(1);
                }
                break;
            case EVERY_YEAR:
                if (this.date.isEqual(date)) {
                    this.date.plusYears(1);
                }
                break;
        }
        this.isWorkTask = false;
        ++id;
        listOfTasks.put(getId(), this); // создание таблицы ключей и значений сразу на моменте создания объектов (задач)
    } //конструктор без String в выборе периодичности

    //getters and setters: =============================================================================================

    public static int getId() {
        return id;
    }

    public String getHeading() {
        return heading;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDataTime() {
        return dataTime;
    }

    public static List<Planner> getList() {
        return list;
    }

    public String getTypes() {
        return howOftenRemind.typesOfRepetitions;
    }

    public Map<Integer, Planner> getListOfTasks() {
        return listOfTasks;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isWorkTask() {
        return isWorkTask;
    }

    public void setWorkTask(boolean workTask) {
        isWorkTask = workTask;
    }


    @Override
    public void oneTime() {

    }

    // методы интерфейса: ==============================================================================================
    @Override
    public void oneTime(LocalDate date) {
        List<String> taskForDate = new ArrayList<>();
        for (Planner planner : getList()) {
            if (planner.date.equals(date)) {
                taskForDate.add(String.valueOf(planner));
            }
        }
        System.out.println(taskForDate);
    }

    @Override
    public void everyDay() {

    }

    @Override
    public void everyWeek() {

    }

    @Override
    public void everyMonth() {

    }

    @Override
    public void everyYear() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planner planner = (Planner) o;
        return isWorkTask == planner.isWorkTask && Objects.equals(heading, planner.heading) && Objects.equals(description, planner.description) && Objects.equals(dataTime, planner.dataTime) && howOftenRemind == planner.howOftenRemind;
    }


    //hashcode, equals & toString: =====================================================================================

    @Override
    public int hashCode() {
        return Objects.hash(heading, description, dataTime, howOftenRemind, isWorkTask);
    }

    @Override
    public String toString() {
        return "Planner{" +
                "heading='" + heading + '\'' +
                ", description='" + description + '\'' +
                ", dataTime=" + dataTime +
                ", howOftenRemind=" + howOftenRemind.typesOfRepetitions +
                ", isWorkTask=" + (isWorkTask ? "Задача по работе." : "Персональная задача.") +
                '}';
    }

    //enum: ============================================================================================================
    public enum TypesOfRepetitions {
        ONE_TIME("Один раз."),
        EVERYDAY("Каждый день."),
        EVERY_WEEK("Каждую неделю."),
        EVERY_MONTH("Каждый месяц."),
        EVERY_YEAR("Каждый год.");

        private final String typesOfRepetitions;

        TypesOfRepetitions(String howOftenRemind) {
            this.typesOfRepetitions = howOftenRemind;
        }

        public static TypesOfRepetitions findByString(String typesOfRepetitions) {
            for (TypesOfRepetitions typesOf : values()) {
                if (typesOf.getTypesOfRepetitions().equals(typesOfRepetitions)) {
                    return typesOf;
                }
            }
            return null;
        }

        public String getTypesOfRepetitions() {
            return typesOfRepetitions;
        }
    }
}
