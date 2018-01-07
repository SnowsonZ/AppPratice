package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.CartGoodsBean;

/**
 * author: snowson
 * created on: 18-1-6 下午2:20
 * description:
 */

public class OrderBodyViewHolder extends TypeHolder<CartGoodsBean> {

    private TextView tv_goods_name;
    private TextView tv_goods_type;
    private TextView tv_goods_count;

    @Override
    public View createView(Context contex) {
        return null;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent, boolean attachToRoot) {
        View convertView = inflater.inflate(R.layout.item_cart_with_footer,
                parent, false);
        tv_goods_name = convertView.findViewById(R.id.tv_goods_name);
        tv_goods_type = convertView.findViewById(R.id.tv_goods_type);
        tv_goods_count = convertView.findViewById(R.id.tv_goods_count);
        return convertView;
    }


    @Override
    public void bindView(Context context, CartGoodsBean bean) {
        if (bean == null) {
            return;
        }
        tv_goods_name.setText(bean.goodsName);
        tv_goods_type.setText(bean.goodsType);
        tv_goods_count.setText(String.valueOf(bean.goodsCount));
    }
}
