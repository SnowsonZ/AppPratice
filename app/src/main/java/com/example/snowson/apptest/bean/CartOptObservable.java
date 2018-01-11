package com.example.snowson.apptest.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by snowson on 18-1-8.
 * 购物车批量操作计算包装类
 */

public class CartOptObservable extends Observable implements Observer {
    public boolean isCheacked;
    public List<ShopObservable> shopObservables = new ArrayList<ShopObservable>();
    private OnChangeOptListener mListener;
    public interface OnChangeOptListener{
        void onUpdateViewState();
    }
    public void setOnChangeOptListener(OnChangeOptListener listener) {
        mListener = listener;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void update(Observable o, Object event) {
        if(event instanceof EventType) {
            EventType<Boolean> eventType = (EventType<Boolean>) event;
            if(eventType.type == EventType.TYPE_ALL) {
                boolean checkFLag = true;
                for (ShopObservable obsShop : shopObservables) {
                    if(!obsShop.isChecked) {
                        checkFLag = false;
                        break;
                    }
                }
                this.isCheacked = checkFLag;
                if(mListener != null) {
                    mListener.onUpdateViewState();
                }
            }
        }
    }

    public void setCheckedChange(boolean isCheacked) {
        this.isCheacked = isCheacked;
        EventType<Boolean> eventType = new EventType<Boolean>();
        eventType.type = EventType.TYPE_ALL;
        eventType.value = this.isCheacked;
        setChanged();
        notifyObservers(eventType);
    }
}
