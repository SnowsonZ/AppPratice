package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.CartGoodsBean;
import com.example.snowson.apptest.utils.TypeData;

/**
 * author: snowson
 * created on: 18-1-6 下午7:02
 * description:
 */

public class OrderHeaderViewHolder extends BaseHeaderViewHolder {

    private TextView tv_shop_name;

    @Override
    public View createView(Context contex) {
        return null;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent, boolean attachToRoot) {
        View convertView = inflater.inflate(R.layout.item_common_header, parent, false);
        tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
        return convertView;
    }

    @Override
    public void bindView(Context context, TypeData bean) {
        if (bean == null) {
            return;
        }
        CartGoodsBean result = (CartGoodsBean) bean;
    }
}
