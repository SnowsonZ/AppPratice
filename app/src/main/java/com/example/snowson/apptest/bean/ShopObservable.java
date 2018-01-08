package com.example.snowson.apptest.bean;

import com.example.snowson.apptest.utils.TypeData;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by snowson on 18-1-4.
 */

public class ShopObservable extends Observable implements Observer, TypeData<CartGoodsObservable> {
    public boolean isEditing;
    public boolean isChecked;
    public ShopObservableSrc shopObservableSrc = new ShopObservableSrc();

    @Override
    public void update(Observable o, Object arg) {
        //全选按钮点击
        if(arg instanceof EventType) {
            EventType eventType = (EventType) arg;
            this.isChecked = (boolean) eventType.value;
            EventType<Boolean> typeChild = new EventType<Boolean>();
            typeChild.type = EventType.TYPE_SELECTED;
            typeChild.value = this.isChecked;
            setChanged();
            notifyObservers(typeChild);
        }else {
            //child checkbox点击
            boolean isFlag = true;
            for (CartGoodsObservable cartGoodsObservable : shopObservableSrc.obsCartGoods) {
                if (!cartGoodsObservable.isChecked) {
                    isFlag = false;
                    break;
                }
            }
            this.isChecked = isFlag;

            EventType<Boolean> eventTypeAll = new EventType<Boolean>();
            eventTypeAll.type = EventType.TYPE_CHECK_ALL;
            eventTypeAll.value = this.isChecked;
            setChanged();
            notifyObservers(eventTypeAll);
        }
    }

    public void setCheckedChange(boolean isChecked) {
        this.isChecked = isChecked;
        //通知child
        EventType<Boolean> eventTypeChild = new EventType<Boolean>();
        eventTypeChild.type = EventType.TYPE_SELECTED;
        eventTypeChild.value = this.isChecked;
        setChanged();
        notifyObservers(eventTypeChild);

        //通知全选按钮
        EventType<Boolean> eventTypeAll = new EventType<Boolean>();
        eventTypeAll.type = EventType.TYPE_CHECK_ALL;
        eventTypeAll.value = this.isChecked;
        setChanged();
        notifyObservers(eventTypeAll);
    }

    public void setEditingChange(boolean isEditing) {
        this.isEditing = isEditing;
        setChanged();
        EventType<Boolean> eventType = new EventType<Boolean>();
        eventType.type = EventType.TYPE_EDITING;
        eventType.value = this.isEditing;
        notifyObservers(eventType);
    }

    @Override
    public CartGoodsObservable getChild(int childPosition) {
        return shopObservableSrc.obsCartGoods.get(childPosition);
    }

    @Override
    public int getChildCount() {
        return shopObservableSrc.obsCartGoods.size();
    }
}
