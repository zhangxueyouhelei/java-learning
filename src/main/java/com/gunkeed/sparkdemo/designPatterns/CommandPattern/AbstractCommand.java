package com.gunkeed.sparkdemo.designPatterns.CommandPattern;

public abstract class AbstractCommand {

    protected final AbstractReceiver receiver;

    public AbstractCommand(AbstractReceiver receiver) {
        this.receiver = receiver;
    }

    public abstract void excute();
}
