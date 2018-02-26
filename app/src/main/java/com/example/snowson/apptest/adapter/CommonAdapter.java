package com.example.snowson.apptest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snowson on 18-2-24.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    Context mContext;
    List<T> mData;
    private LayoutInflater mLayoutInflater;

    public CommonAdapter(Context context, ArrayList<T> data) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
