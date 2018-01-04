package com.example.snowson.apptest.bean;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by snowson on 18-1-4.
 */

public class CartObservable extends Observable implements Observer {
    public boolean isChecked;
//    public List<CartGoodsObservable> obsCartGoods;
    public ShopBean cartBean;

    @Override
    public void update(Observable o, Object arg) {
        boolean isFlag = true;
        for (CartGoodsObservable cartGoodsObservable : obsCartGoods) {
            if (!cartGoodsObservable.isChecked) {
                isFlag = false;
                break;
            }
        }
        isChecked = isFlag;
    }

    public void setCheckedChange() {
        isChecked = !isChecked;
        notifyObservers(isChecked);
    }
}
