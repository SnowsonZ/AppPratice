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
    @SuppressWarnings("unchecked")
    public void update(Observable o, Object eventType) {
        if(eventType instanceof EventType) {
            EventType<Boolean> resultType = (EventType<Boolean>) eventType;
            EventType<Boolean> postType = new EventType<Boolean>();
            switch (resultType.type) {
                //接收到全选按钮事件，通知child更新状态
                case EventType.TYPE_ALL:
                    this.isChecked = resultType.value;
                    postType.type = EventType.TYPE_CHILD;
                    postType.value = isChecked;
                    break;
                //接收到child事件，通知全选按钮状态刷新
                case EventType.TYPE_CHILD:
                    boolean isFlag = true;
                    for (CartGoodsObservable cartGoodsObservable : shopObservableSrc.obsCartGoods) {
                        if (!cartGoodsObservable.isChecked) {
                            isFlag = false;
                            break;
                        }
                    }
                    this.isChecked = isFlag;
                    postType.type = EventType.TYPE_ALL;
                    postType.value = this.isChecked;
                    break;
                default:
                    break;
            }
            setChanged();
            notifyObservers(postType);
        }
    }

    public void setCheckedChange(boolean isChecked) {
        this.isChecked = isChecked;
        //通知child
        EventType<Boolean> eventTypeChild = new EventType<Boolean>();
        eventTypeChild.type = EventType.TYPE_CHILD;
        eventTypeChild.value = this.isChecked;
        setChanged();
        notifyObservers(eventTypeChild);

        //通知全选按钮
        EventType<Boolean> eventTypeAll = new EventType<Boolean>();
        eventTypeAll.type = EventType.TYPE_ALL;
        eventTypeAll.value = this.isChecked;
        setChanged();
        notifyObservers(eventTypeAll);
    }

    public void setEditingChange(boolean isEditing) {
        this.isEditing = isEditing;
        setChanged();
        EventType<Boolean> eventType = new EventType<Boolean>();
        eventType.type = EventType.TYPE_GROUP_EDITING;
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
