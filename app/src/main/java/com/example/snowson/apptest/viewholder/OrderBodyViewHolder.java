package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.OrderItemBean;

/**
 * author: snowson
 * created on: 18-1-6 下午2:20
 * description: 订单body组件ViewHolder
 */

public class OrderBodyViewHolder extends TypeHolder<OrderItemBean> {

    private TextView mGoodsNameTv;
    private TextView mGoodsTypeTv;
    private TextView mGoodsCountTv;


    @Override
    public View createView(Context contex) {
        return null;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent, boolean attachToRoot) {
        View convertView = inflater.inflate(R.layout.item_order_with_footer,
                parent, false);
        mGoodsNameTv = convertView.findViewById(R.id.tv_goods_name);
        mGoodsTypeTv = convertView.findViewById(R.id.tv_goods_type);
        mGoodsCountTv = convertView.findViewById(R.id.tv_goods_count);
        return convertView;
    }


    @Override
    public void bindView(Context context, OrderItemBean bean) {
        if (bean == null) {
            return;
        }
        mGoodsNameTv.setText(bean.goodsBean.goodsName);
        mGoodsTypeTv.setText(bean.goodsBean.goodsType);
        mGoodsCountTv.setText(String.valueOf(bean.goodsBean.goodsCount));
    }
}
