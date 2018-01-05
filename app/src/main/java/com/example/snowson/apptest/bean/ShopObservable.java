package com.example.snowson.apptest.bean;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by snowson on 18-1-4.
 */

public class ShopObservable extends Observable implements Observer {
    public boolean isEditing;
    public boolean isChecked;
    public ShopObservableSrc shopObservableSrc = new ShopObservableSrc();

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
        setChanged();
        EventType<Boolean> eventType = new EventType<Boolean>();
        eventType.type = EventType.TYPE_SELECTED;
        eventType.value = this.isChecked;
        notifyObservers(eventType);
    }

    public void setEditingChange(boolean isEditing) {
        this.isEditing = isEditing;
        setChanged();
        EventType<Boolean> eventType = new EventType<Boolean>();
        eventType.type = EventType.TYPE_EDITING;
        eventType.value = this.isEditing;
        notifyObservers(eventType);
    }
}
