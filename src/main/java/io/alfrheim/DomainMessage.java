package io.alfrheim;

public class DomainMessage {

    private final DomainUser domainUser;
    private final String message;
    private final Clock created;



    public DomainMessage(DomainUser domainUser, String message, Clock clock) {
        this.domainUser = domainUser;
        this.message = message;
        this.created = clock;
    }

    @Override
    public String toString() {
        String formattedTime = created.elapsedTimeFormatted();
        return String.format("%s - %s %s", this.getDomainUser().getName(), this.getMessage(), formattedTime);
    }


    public String getMessage() {
        return message;
    }

    public DomainUser getDomainUser() {
        return domainUser;
    }

    public Clock getCreated() {
        return created;
    }
}
