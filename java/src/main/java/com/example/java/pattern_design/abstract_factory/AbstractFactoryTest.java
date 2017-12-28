package com.example.java.pattern_design.abstract_factory;

/**
 * Created by snowson on 17-12-28.
 */

public class AbstractFactoryTest {

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private String className;

    private IFactory createFactory() {
        IFactory factory = null;
        try {
            factory = (IFactory) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return factory;
    }


    public static void main(String[] args) {
        AbstractFactoryTest test = new AbstractFactoryTest();
        test.setClassName("com.example.java.pattern_design.abstract_factory.ConcreteFactoryThree");
        IFactory factory = test.createFactory();
        factory.createProductA().printInfo("This is the ProductA");
        factory.createProductB().printDebug("This is the ProductB");
    }
}
