package manfred.end.testability.create.interfaze.old;

import java.time.LocalDateTime;

public class Times {
    public static String getTimeOfDay() {
        LocalDateTime time = LocalDateTime.now();
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
