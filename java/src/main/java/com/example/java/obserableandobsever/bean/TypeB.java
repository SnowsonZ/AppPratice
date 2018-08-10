package com.example.java.obserableandobsever.bean;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by snowson on 18-1-5.
 */

public class TypeB implements Observer{
    public int number;

    @Override
    public void update(Observable observable, Object o) {
        System.out.println("A发生了变化");
        if(o instanceof Integer) {
            number = (int) o;
        }
    }
}
