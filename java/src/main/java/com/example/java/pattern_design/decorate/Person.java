package com.example.java.pattern_design.decorate;

/**
 * Created by snowson on 18-3-1.
 */

public class Person {
    String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void show() {
        System.out.print("decorate complete!!!");
    }
}
