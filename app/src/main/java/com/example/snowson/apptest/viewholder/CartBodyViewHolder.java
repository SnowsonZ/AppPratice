package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.CartGoodsBean;
import com.example.snowson.apptest.bean.CartGoodsObservable;

/**
 * author: snowson
 * created on: 18-1-6 下午2:20
 * description:
 */

public class CartBodyViewHolder extends TypeHolder<CartGoodsObservable> {
    private TextView tv_goods_name;
    private TextView tv_goods_type;
    private TextView tv_goods_count;
    private TextView tv_goods_type_edit;
    private TextView tv_goods_count_edit;
    private CheckBox cb_select;
    private RelativeLayout rlayout_display;
    private RelativeLayout rlayout_edit;

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
        tv_goods_type_edit = convertView.findViewById(R.id.tv_goods_type_edit);
        tv_goods_count_edit = convertView.findViewById(R.id.tv_goods_count_edit);
        cb_select = convertView.findViewById(R.id.cb_goods_check);
        rlayout_edit = convertView.findViewById(R.id.rlayout_edit);
        rlayout_display = convertView.findViewById(R.id.rlayout_display);
        convertView.setTag(this);

        return convertView;
    }

    @Override
    public void bindView(Context context, final CartGoodsObservable bean) {
        if (bean == null) {
            return;
        }
        CartGoodsBean cartGoodsBean = bean.cartGoodsBean;
        tv_goods_name.setText(cartGoodsBean.goodsName);
        tv_goods_type.setText(cartGoodsBean.goodsType);
        tv_goods_count.setText(String.valueOf(cartGoodsBean.goodsCount));

        if (bean.isEditing) {
            rlayout_display.setVisibility(View.GONE);
            rlayout_edit.setVisibility(View.VISIBLE);
        } else {
            rlayout_edit.setVisibility(View.GONE);
            rlayout_display.setVisibility(View.VISIBLE);
        }

        cb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setCheckedChange(cb_select.isChecked());
                if (mListener != null) {
                    mListener.shouldUpdateData();
                }
            }
        });
        cb_select.setChecked(bean.isChecked);
        tv_goods_name.setText(cartGoodsBean.goodsName);
        tv_goods_type.setText(cartGoodsBean.goodsType);
        tv_goods_count.setText(String.valueOf(cartGoodsBean.goodsCount));
        tv_goods_count_edit.setText(String.valueOf(cartGoodsBean.goodsCount));
        tv_goods_type_edit.setText(cartGoodsBean.goodsType);
    }
}
