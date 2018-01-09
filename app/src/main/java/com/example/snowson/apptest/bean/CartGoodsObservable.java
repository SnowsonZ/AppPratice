package com.example.snowson.apptest.bean;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by snowson on 18-1-4.
 */

public class CartGoodsObservable extends Observable implements Observer {
    public boolean isEditing;
    public boolean isChecked;
    public CartGoodsBean cartGoodsBean = new CartGoodsBean();

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
