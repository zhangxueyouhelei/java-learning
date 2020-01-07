package com.gunkeed.sparkdemo.designPatterns.TemplatePattern;

public abstract class AbstractTemplate {

    protected abstract void doSomething();

    protected abstract void doAnything();

    public void templateMethod(){
        this.doSomething();
        if (this.hookMEethod()) {
            this.doAnything();
        }
    }

    /**
     *  钩子方法留给子类实现，扩张templateMethod
     * @return
     */
    protected boolean hookMEethod(){
        return true;
    }
}
