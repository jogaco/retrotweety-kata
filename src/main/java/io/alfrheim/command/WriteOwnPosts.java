package io.alfrheim.command;

import io.alfrheim.DomainMessage;
import io.alfrheim.TweetContext;

import java.util.Optional;

public class WriteOwnPosts extends Command {

    @Override
    public boolean isCommand(String command) {
        return true;
    }

    public String getCommandName() {
        throw new RuntimeException("Does not have CommandName");
    }

    @Override
    protected void executeCommand(String command, TweetContext context) {
        Optional.ofNullable(context.getDomainUser(command)).ifPresent(
                u -> u.getDomainMessages().stream()
                        .map(DomainMessage::toString)
                        .forEach(context.getConsole()::printLine));

    }

}