package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by snowson on 18-2-24.
 * 1. 存储View
 */

public class CommonViewHolder {
    private final SparseArray<View> mViews;
    private View mConvertView;

    public CommonViewHolder(Context context, int layoutId, ViewGroup parent) {
        mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static CommonViewHolder get(Context context, View convert, int layoutId, ViewGroup parent) {
        if (convert == null) {
            return new CommonViewHolder(context, layoutId, parent);
        }
        return (CommonViewHolder) convert.getTag();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }
}
