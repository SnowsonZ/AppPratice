package com.example.java.pattern_design.abstract_factory;

/**
 * Created by snowson on 17-12-28.
 * 具体产品A
 */

public class ProductA implements AbstractProductA {
    private String factoryIdentity;

    public String getFactoryIdentity() {
        return factoryIdentity;
    }

    public void setFactoryIdentity(String factoryIdentity) {
        this.factoryIdentity = factoryIdentity;
    }

    @Override
    public void printInfo(String info) {
        System.out.println("当前打印级别：info, " + info + ", factoryIdentity: " + factoryIdentity);
    }
}
