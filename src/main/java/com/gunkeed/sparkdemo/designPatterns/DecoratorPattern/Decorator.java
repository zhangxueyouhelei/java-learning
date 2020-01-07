package com.gunkeed.sparkdemo.designPatterns.DecoratorPattern;

/**
 * 抽象装饰类
 *
 */
public abstract  class Decorator extends Component{

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }


    @Override
    public void operate() {
        this.component.operate();
    }
}
