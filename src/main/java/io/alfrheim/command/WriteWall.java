package io.alfrheim.command;

import io.alfrheim.DomainMessage;
import io.alfrheim.DomainUser;
import io.alfrheim.TweetContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class WriteWall extends Command {
    private static final Comparator<DomainMessage> POSTS_DESC =
            (m1, m2) -> m1.getCreated().elapsedTime().compareTo(m2.getCreated().elapsedTime());
    public static final String COMMAND_NAME = "wall";

    @Override
    public boolean isCommand(String command) {
        return command.contains(COMMAND_NAME);
    }

    @Override
    protected void executeCommand(String command, TweetContext context) {
        String userName = getUserName(command);

        List<DomainMessage> posts = context.getFollows(userName).stream()
                .map(DomainUser::getDomainMessages)
                .collect(ArrayList::new, List::addAll, List::addAll);

        posts.addAll(context.getUserDomainMessages(userName));
        posts.sort(POSTS_DESC);

        Optional.of(posts).ifPresent(
                u -> u.stream()
                        .map(DomainMessage::toString)
                        .forEach(context.getConsole()::printLine));
    }

    protected String getUserName(String command) {
        String[] split = command.split(" ");
        return split[0].trim();
    }

    @Override
    protected String getCommandName() {
        return COMMAND_NAME;
    }

}