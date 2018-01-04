package com.example.snowson.apptest.bean;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by snowson on 18-1-4.
 */

public class ShopObservable extends Observable implements Observer {
    public boolean isChecked;
    public ShopObservableSrc shopObservableSrc;
    public List<>

    @Override
    public void update(Observable o, Object arg) {
        boolean isFlag = true;
        for (CartGoodsObservable cartGoodsObservable : shopObservableSrc.obsCartGoods) {
            if (!cartGoodsObservable.isChecked) {
                isFlag = false;
                break;
            }
        }
        isChecked = isFlag;
    }

    public void setCheckedChange(boolean isChecked) {
        this.isChecked = isChecked;
        notifyObservers(this.isChecked);
    }
}
