package com.example.java.pattern_design.decorate;

/**
 * Created by snowson on 18-3-1.
 */

public class Test {
    public static void main(String[] args) {
        Person person = new Person("snowson");
        Hat hat = new Hat();
        TShirt tShirt = new TShirt();
        hat.setPerson(person);
        tShirt.setPerson(hat);
        tShirt.show();
    }
}
