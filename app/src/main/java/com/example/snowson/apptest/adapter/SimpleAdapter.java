package com.example.snowson.apptest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.DataTypeTwo;
import com.example.snowson.apptest.viewholder.CommonViewHolder;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by snowson on 18-2-24.
 */

public class SimpleAdapter<T> extends CommonAdapter<T> {

    public SimpleAdapter(Context context, ArrayList<T> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Logger.d("getView:" + position);
        CommonViewHolder viewHolder = CommonViewHolder
                .get(mContext, convertView, R.layout.item_type_two, parent);
        ImageView imgView = viewHolder.getView(R.id.iv_tiny);
        TextView tvName = viewHolder.getView(R.id.tv_name);
        DataTypeTwo item = (DataTypeTwo) mData.get(position);
        imgView.setBackgroundColor(mContext.getResources().getColor(item.getColorTinyPic()));
        tvName.setText(item.getTinyName());
        return viewHolder.getConvertView();
    }
}
