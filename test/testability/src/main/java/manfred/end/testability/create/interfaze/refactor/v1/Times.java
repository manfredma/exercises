package manfred.end.testability.create.interfaze.refactor.v1;

import java.time.LocalDateTime;

public class Times {
    public static String getTimeOfDay(LocalDateTime time) {
        int hour = time.getHour();
        if (hour < 6) {
            return "Night";
        } else if (hour < 12) {
            return "Morning";
        } else if (hour < 18) {
            return "Afternoon";
        }
        return "Evening";
    }
}
