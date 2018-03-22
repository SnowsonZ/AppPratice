package com.example.java.pattern_design.decorate;

/**
 * Created by snowson on 18-3-1.
 */

public class Finery extends Person {
    Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void show() {
        if (person != null) {
            person.show();
        }
    }
}
