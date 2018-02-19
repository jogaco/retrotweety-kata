package io.alfrheim;

import java.time.ZonedDateTime;

public class DomainMessage {

    private final DomainUser domainUser;
    private final String message;
    private final ZonedDateTime created;
    private final Clock clock;


    public DomainMessage(DomainUser domainUser, String message, Clock clock) {
        this(domainUser, message, clock, clock.getTime());

    }

    public DomainMessage(DomainUser domainUser, String message, Clock clock, ZonedDateTime created) {
        this.domainUser = domainUser;
        this.message = message;
        this.clock = clock;
        this.created = created;
    }

    @Override
    public String toString() {
        String formattedTime = clock.elapsedTime(this.created);
        return String.format("%s - %s %s", this.getDomainUser().getName(), this.getMessage(), formattedTime);
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
