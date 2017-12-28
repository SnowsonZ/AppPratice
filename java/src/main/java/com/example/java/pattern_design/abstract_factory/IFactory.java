package com.example.java.pattern_design.abstract_factory;

/**
 * Created by snowson on 17-12-28.
 * 抽象工厂   由同一个工厂产出的产品属于同一个产品族
 * 抽象工厂的特点在于一个工厂可以产出多种产品，这些产品同属于一个产品族
 *
 * 解决 多个产品等级结构的问题 eg
 */

public interface IFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}
