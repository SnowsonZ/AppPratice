package com.example.snowson.apptest.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by snowson on 18-1-8.
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
    public void update(Observable o, Object event) {
        if(event instanceof EventType) {
            EventType eventType = (EventType) event;
            if(eventType.type == EventType.TYPE_CHECK_ALL) {
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
        setChanged();
        EventType<Boolean> eventType = new EventType<Boolean>();
        eventType.type = EventType.TYPE_CHECK_ALL;
        eventType.value = this.isCheacked;
        notifyObservers(eventType);
    }
}
