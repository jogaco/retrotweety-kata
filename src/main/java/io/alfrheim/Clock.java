package io.alfrheim;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Clock {
    ZonedDateTime getTime() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public String elapsedTime(ZonedDateTime start) {
        ZonedDateTime currentDate = getTime();

        Duration duration = Duration.between(start, currentDate);
        long elapsedTime;
        String time;
        if (duration.toMinutes() > 0) {
            elapsedTime = duration.toMinutes();
            time = "minute";
            if(isPlural(elapsedTime)) {
                time += "s";
            }

        } else  {
            elapsedTime = duration.getSeconds();
            time = "second";
            if(isPlural(elapsedTime)) {
                time += "s";
            }

        }

        return String.format("(%d %s ago)", elapsedTime, time);
    }

    private boolean isPlural(long elapsedTime) {
        return elapsedTime > 1;
    }

}