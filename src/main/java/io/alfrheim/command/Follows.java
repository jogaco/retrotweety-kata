package io.alfrheim.command;

import io.alfrheim.DomainUser;
import io.alfrheim.TweetContext;

public class Follows extends Command {

    public static final String COMMAND_NAME = "follows";

    @Override
    public boolean isCommand(String command) {
        return command.contains(COMMAND_NAME);
    }

    @Override
    protected void executeCommand(String command, TweetContext context) {
        String userName = getUserName(command);
        String userNameToFollow = getCommandParam(command);

        DomainUser user = context.getDomainUser(userName);
        DomainUser userToFollow = context.getDomainUser(userNameToFollow);

        user.follow(userToFollow);
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

}