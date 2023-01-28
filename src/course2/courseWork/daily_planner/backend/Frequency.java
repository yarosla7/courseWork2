package course2.courseWork.daily_planner.backend;

import java.time.LocalDate;

public interface Frequency {
    void oneTime();

    // методы интерфейса: ==============================================================================================
    void oneTime(LocalDate date);

    void everyDay();

    void everyWeek();

    void everyMonth();

    void everyYear();

    class ServiceClassOfPlanner {
    }
}
