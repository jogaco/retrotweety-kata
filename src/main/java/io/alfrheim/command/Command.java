package io.alfrheim.command;

import io.alfrheim.TweetContext;

public abstract class Command {

    private Command next;

    public Command setNext(Command next) {
        this.next = next;
        return this.next;
    }

    public Command getNext() {
        return next;
    }
    abstract protected boolean isCommand(String command);
    abstract protected void executeCommand(String command, TweetContext context);
    abstract protected String getCommandName();


    protected String getUserName(String command) {
        String[] split = command.split(getCommandName());
        return split[0].trim();
    }

    protected String getCommandParam(String command) {
        String[] split = command.split(getCommandName());
        return split[1].trim();
    }

    public void execute(String command, TweetContext context) {
        Command current = this;
        while (current != null) {
            if (current.isCommand(command)) {
                current.executeCommand(command, context);
                return;
            } else {
                current = current.getNext();
            }
        }
    }

}