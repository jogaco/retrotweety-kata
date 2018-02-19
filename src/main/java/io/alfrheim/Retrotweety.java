package io.alfrheim;

import io.alfrheim.command.Follows;
import io.alfrheim.command.SavePost;
import io.alfrheim.command.WriteOwnPosts;
import io.alfrheim.command.WriteWall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Retrotweety implements TweetContext {

    private final Console console;

    private final Clock clock;
    private final SavePost commands;

    private Map<String, DomainUser> users = new HashMap<>();

    public Retrotweety(Console console, Clock clock) {
        this.console = console;
        this.clock = clock;
        this.commands = new SavePost();
        this.commands.setNext(new WriteWall()).setNext(new Follows()).setNext(new WriteOwnPosts());

    }

    public void command(String commandLine) {

        commands.execute(commandLine, this);
    }

    public DomainUser getDomainUser(String userName) {
        DomainUser user;
        if(users.containsKey(userName)) {
            user = users.get(userName);
        } else {
            users.put(userName, new DomainUser(userName));
            user = users.get(userName);
        }
        return user;
    }

    public Clock getClock() {
        return clock;
    }

    @Override
    public Console getConsole() {
        return console;
    }

    @Override
    public Set<DomainUser> getFollows(String userName) {
        DomainUser domainUser = getDomainUser(userName);

        return domainUser.getFollows();
    }

    @Override
    public List<DomainMessage> getUserDomainMessages(String userName) {
        DomainUser domainUser = getDomainUser(userName);

        return domainUser.getDomainMessages();
    }
}
