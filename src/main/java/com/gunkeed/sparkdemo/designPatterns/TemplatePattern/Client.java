package com.gunkeed.sparkdemo.designPatterns.TemplatePattern;

public class Client {

    public static void main(String[] args) {
        AbstractTemplate concreteTemplate1 = new ConcreteTemplate1();
        AbstractTemplate concreteTemplate2 = new ConcreteTemplate2();

        concreteTemplate1.templateMethod();
        concreteTemplate2.templateMethod();
    }
}
