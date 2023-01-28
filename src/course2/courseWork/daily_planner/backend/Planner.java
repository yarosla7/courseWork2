package course2.courseWork.daily_planner.backend;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Planner implements Frequency {
    private static int id; //уникальный номер каждого таска
    private final String heading;
    private final String description;
    private final LocalDateTime dataTime;
    private final TypesOfRepetitions howOftenRemind;
    private final Map<Integer, Planner> listOfTasks = new HashMap<>();
    private boolean isWorkTask;

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
        this.isWorkTask = false;
        listOfTasks.put(getId(), this); // создание таблицы ключей и значений сразу на моменте создания объектов (задач)
        ++id;
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
        this.isWorkTask = isWorkTask;
        listOfTasks.put(getId(), this); // создание таблицы ключей и значений сразу на моменте создания объектов (задач)
        ++id;
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
        this.isWorkTask = false;
        listOfTasks.put(getId(), this); // создание таблицы ключей и значений сразу на моменте создания объектов (задач)
        ++id;
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

    public String getTypes() {
        return howOftenRemind.typesOfRepetitions;
    }

    public Map<Integer, Planner> getListOfTasks() {
        return listOfTasks;
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
