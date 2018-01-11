package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.OrderBean;
import com.example.snowson.apptest.bean.OrderStatus;
import com.example.snowson.apptest.utils.TypeData;

/**
 * author: snowson
 * created on: 18-1-6 下午7:02
 * description: 订单header组件ViewHolder
 */

public class OrderHeaderViewHolder extends BaseHeaderViewHolder {

    private TextView tv_shop_name;
    private TextView tv_order_status;

    @Override
    public View createView(Context contex) {
        return null;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent, boolean attachToRoot) {
        View convertView = inflater.inflate(R.layout.item_common_header, parent, false);
        tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
        tv_order_status = convertView.findViewById(R.id.tv_shop_opt);
        tv_order_status.setTextColor(Color.RED);
        tv_order_status.setTextSize(14);
        convertView.findViewById(R.id.cb_shop_check).setVisibility(View.GONE);
        return convertView;
    }

    @Override
    public void bindView(Context context, TypeData bean) {
        if (bean == null) {
            return;
        }
        OrderBean result = (OrderBean) bean;
        switch (result.status) {
            case OrderStatus.STATUS_UNPAY:
                tv_order_status.setText("等待买家付款");
                break;
            case OrderStatus.STATUS_UNPASS:
                tv_order_status.setText("买家已付款");
                break;
            case OrderStatus.STATUS_UNGETTED:
                tv_order_status.setText("卖家已发货");
                break;
            case OrderStatus.STATUS_UNCOMMENT:
                tv_order_status.setText("交易成功");
                break;
            case OrderStatus.STATUS_CANCEL:
                tv_order_status.setText("交易取消");
                tv_order_status.setVisibility(View.INVISIBLE);
                break;
        }
        tv_shop_name.setText(result.shopName);
    }
}
