package course2.courseWork.daily_planner.backend;

import java.time.LocalDate;

public interface Frequency {

    // методы интерфейса: ==============================================================================================

    LocalDate getTaskNextTime(LocalDate date);

}
