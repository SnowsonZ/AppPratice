package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.snowson.apptest.OnNotifyDataChangeListener;

/**
 * Created by snowson on 17-12-27.
 */

public abstract class TypeHolder<T> {

    protected OnNotifyDataChangeListener mListener;

    public abstract View createView(Context contex);

    public abstract View createView(LayoutInflater inflater, ViewGroup parent, boolean attachToRoot);

    public abstract void bindView(Context context, T bean);

    public abstract void bindView(Context context, T bean, int position);

    public void setOnNotifyDataChangeListener(OnNotifyDataChangeListener listener) {
        mListener = listener;
    }
}
