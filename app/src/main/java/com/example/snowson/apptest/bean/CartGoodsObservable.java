package com.example.snowson.apptest.bean;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by snowson on 18-1-4.
 * 购物车单一商品包装类
 */

public class CartGoodsObservable extends Observable implements Observer {
    public boolean isEditing;
    public boolean isChecked;
    public GoodsBean cartGoodsBean = new GoodsBean();

    @Override
    public void update(Observable o, Object event) {
        if (event instanceof EventType) {
            EventType eventType = (EventType) event;
            switch (eventType.type) {
                case EventType.TYPE_CHILD:
                    this.isChecked = (boolean) eventType.value;
                    break;
                case EventType.TYPE_GROUP_EDITING:
                    this.isEditing = (boolean) eventType.value;
                    break;
            }
        }
    }

    public void setCheckedChange(boolean isChecked) {
        this.isChecked = isChecked;
        EventType<Boolean> eventType = new EventType<Boolean>();
        eventType.type = EventType.TYPE_CHILD;
        eventType.value = this.isChecked;
        setChanged();
        notifyObservers(eventType);
    }
}
