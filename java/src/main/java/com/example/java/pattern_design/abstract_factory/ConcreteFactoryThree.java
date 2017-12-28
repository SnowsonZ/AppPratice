package com.example.java.pattern_design.abstract_factory;

/**
 * Created by snowson on 17-12-28.
 */

public class ConcreteFactoryThree implements IFactory {
    @Override
    public AbstractProductA createProductA() {
        ProductA productA = new ProductA();
        productA.setFactoryIdentity(getClass().getName());
        return productA;
    }

    @Override
    public AbstractProductB createProductB() {
        ProductB productB = new ProductB();
        productB.setFactoryIdentity(getClass().getName());
        return productB;
    }
}
