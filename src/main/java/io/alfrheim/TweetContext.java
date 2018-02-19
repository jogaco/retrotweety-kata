package io.alfrheim;

import java.util.List;
import java.util.Set;

public interface TweetContext {

    DomainUser getDomainUser(String userName);
    Set<DomainUser> getFollows(String userName);
    List<DomainMessage> getUserDomainMessages(String userName);
    Clock getClock();
    Console getConsole();

}