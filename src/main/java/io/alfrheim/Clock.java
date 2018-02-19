package io.alfrheim;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Clock implements Comparable<Clock> {
    private final ZonedDateTime startTime;

    Clock() {
        this.startTime = getTime();
    }

    private ZonedDateTime getTime() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public String elapsedTimeFormatted() {
        Duration duration = elapsedTime();
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

    public Duration elapsedTime() {
        ZonedDateTime currentDate = getTime();

        return Duration.between(this.startTime, currentDate);
    }

    private boolean isPlural(long elapsedTime) {
        return elapsedTime > 1;
    }

    @Override
    public int compareTo(Clock o) {
        return o.elapsedTime().compareTo(this.elapsedTime());
    }
}