package course2.courseWork.daily_planner.backend;

import java.time.LocalDateTime;

public interface Frequency {

    // методы интерфейса: ==============================================================================================

    LocalDateTime getTaskNextTime(LocalDateTime dateTime);

}
