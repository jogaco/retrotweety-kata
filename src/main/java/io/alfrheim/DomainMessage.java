package io.alfrheim;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DomainMessage {

    private final DomainUser domainUser;
    private final String message;
    private final ZonedDateTime created;

    public DomainMessage(DomainUser domainUser, String message) {
        this(domainUser, message, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    public DomainMessage(DomainUser domainUser, String message, ZonedDateTime created) {
        this.domainUser = domainUser;
        this.message = message;
        this.created = created;
    }

    @Override
    public String toString() {
        String formattedTime = this.getProperTimeFormat(this.getCreated());
        return String.format("%s - %s %s", this.getDomainUser().getName(), this.getMessage(), formattedTime);
    }

    private Duration elapsedTime(ZonedDateTime time) {
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of("UTC"));

        return Duration.between(time, currentDate);
    }

    private String getProperTimeFormat(ZonedDateTime zonedDateTime) {
        Duration duration = this.elapsedTime(zonedDateTime);
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

    public String getMessage() {
        return message;
    }

    public DomainUser getDomainUser() {
        return domainUser;
    }

    public ZonedDateTime getCreated() {
        return created;
    }
}
