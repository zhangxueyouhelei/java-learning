package com.gunkeed.sparkdemo.designPatterns.CommandPattern;

public class Invoker {

    private AbstractCommand command;

    public AbstractCommand getCommand() {
        return command;
    }

    public void setCommand(AbstractCommand command) {
        this.command = command;
    }

    public void  doAction(){
        this.command.excute();
    }
}
