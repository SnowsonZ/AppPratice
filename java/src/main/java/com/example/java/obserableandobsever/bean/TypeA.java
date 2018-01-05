package com.example.java.obserableandobsever.bean;

import java.util.Observable;

/**
 * Created by snowson on 18-1-5.
 */

public class TypeA extends Observable{
    public int num;

    public void setChange(int num) {
       this.num = num;
       this.setChanged();
       notifyObservers(num);
    }

}
