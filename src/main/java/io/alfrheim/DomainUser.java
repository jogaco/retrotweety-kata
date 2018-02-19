package io.alfrheim;

import java.util.*;

public class DomainUser {
    private String name;
    private Set<DomainUser> follows;
    private List<DomainMessage> domainMessages = new ArrayList<>();

    public DomainUser(String name) {
        this(name, new HashSet<>());
    }

    public DomainUser(String name, Set<DomainUser> follows) {
        this.name = name;
        this.follows= Optional.ofNullable(follows).orElse(new HashSet<>());
    }

    public String getName() {
        return name;
    }

    public DomainUser setName(String name) {
        this.name = name;
        return this;
    }

    public DomainUser follow(DomainUser userToFollow) {
        this.getFollows().add(userToFollow);
        return this;
    }

    public Set<DomainUser> getFollows() {
        return follows;
    }

    public void write(DomainMessage message) {
        this.domainMessages.add(message);
    }

    public List<DomainMessage> getDomainMessages() {
        return domainMessages;
    }
}
