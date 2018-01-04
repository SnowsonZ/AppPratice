package com.example.snowson.apptest.bean;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by snowson on 18-1-4.
 */

public class CartGoodsObservable extends Observable implements Observer {
    public boolean isChecked;
    public CartGoodsBean cartGoodsBean;

    @Override
    public void update(Observable o, Object isChecked) {
        if (isChecked instanceof Boolean) {
            this.isChecked = (boolean) isChecked;
        }
    }

    public void setCheckedChange(boolean isChecked) {
        this.isChecked = isChecked;
        notifyObservers();
    }
}
