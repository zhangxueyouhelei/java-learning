package com.gunkeed.sparkdemo.designPatterns.TemplatePattern;

public class ConcreteTemplate1 extends AbstractTemplate {


    private boolean hookValue = true;

    @Override
    protected void doSomething() {

    }

    @Override
    protected void doAnything() {

    }

    @Override
    protected boolean hookMEethod() {
        return this.hookValue;
    }


    public boolean getHookValue() {
        return hookValue;
    }

    public void setHookValue(boolean hookValue) {
        this.hookValue = hookValue;
    }
}
