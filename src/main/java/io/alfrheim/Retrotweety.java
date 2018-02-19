package io.alfrheim;

import java.util.*;

public class Retrotweety {

    private final Console console;

    private static final  Comparator<DomainMessage> POSTS_DESC =
            (m1, m2) -> m2.getCreated().compareTo(m1.getCreated());

    private Map<String, DomainUser> users = new HashMap<>();

    public Retrotweety(Console console) {
        this.console = console;
    }

    public void command(String commandLine) {
        if(commandLine.contains("->")) {
            //save post
            String[] split = commandLine.split("->");
            String userName = split[0].trim();

            DomainUser user = getDomainUser(userName);

            String postMessage = split[1].trim();
            user.write(new DomainMessage(user, postMessage));

        } else if (commandLine.contains("wall")) {
            //write wall
            String[] split = commandLine.split(" ");
            String userName = split[0].trim();
             List<DomainMessage> posts = users.get(userName).getFollows().stream()
                .map(DomainUser::getDomainMessages)
                .collect(ArrayList::new, List::addAll, List::addAll);
            posts.addAll(users.get(userName).getDomainMessages());
            posts.sort(POSTS_DESC);

             Optional.of(posts).ifPresent(
                    u -> u.stream()
                            .map(DomainMessage::toString)
                            .forEach(console::printLine));

        } else if (commandLine.contains("follows")) {
            //follow user
            String[] split = commandLine.split("follows");
            String userName = split[0].trim();

            DomainUser user = getDomainUser(userName);

            String userNameToFollow = split[1].trim();
            DomainUser userToFollow = getDomainUser(userNameToFollow);

            user.follow(userToFollow);
        } else {
            //write own posts
            Optional.ofNullable(users.get(commandLine)).ifPresent(
                    u -> u.getDomainMessages().stream()
                            .map(DomainMessage::toString)
                            .forEach(console::printLine));
        }
    }

    private DomainUser getDomainUser(String userName) {
        DomainUser user;
        if(users.containsKey(userName)) {
            user = users.get(userName);
        } else {
            users.put(userName, new DomainUser(userName));
            user = users.get(userName);
        }
        return user;
    }
}
