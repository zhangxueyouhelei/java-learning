package com.gunkeed.sparkdemo.designPatterns.CommandPattern;

public class ConcerteCommand1 extends AbstractCommand {

    public ConcerteCommand1() {
        super(new ConcreteReciever1());
    }

    public ConcerteCommand1(AbstractReceiver receiver) {
        super(receiver);
    }

    @Override
    public void excute() {
        super.receiver.doSomething();
    }


}
