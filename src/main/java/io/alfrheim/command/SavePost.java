package io.alfrheim.command;

import io.alfrheim.DomainMessage;
import io.alfrheim.DomainUser;
import io.alfrheim.TweetContext;

public class SavePost extends Command {

    public static final String COMMAND_NAME = "->";

    @Override
    public boolean isCommand(String command) {
        return command.contains(COMMAND_NAME);
    }

    @Override
    public void executeCommand(String command, TweetContext context) {
        String userName = getUserName(command);
        String postMessage = getCommandParam(command);

        DomainUser user = context.getDomainUser(userName);

        user.write(new DomainMessage(user, postMessage, context.getClock()));
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }
}