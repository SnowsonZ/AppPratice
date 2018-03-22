package com.example.java.pattern_design.decorate;

/**
 * Created by snowson on 18-3-1.
 */

public class TShirt extends Finery {

    @Override
    public void show() {
        System.out.print("TShirt ");
        super.show();
    }
}
