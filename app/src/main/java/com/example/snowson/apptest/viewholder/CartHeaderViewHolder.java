package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.snowson.apptest.OnNotifyDataChangeListener;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.ShopObservable;

/**
 * author: snowson
 * created on: 18-1-6 下午7:02
 * description: 购物车header ViewHolder
 */

public class CartHeaderViewHolder extends TypeHolder<ShopObservable> {

    private TextView tv_shop_name;
    private TextView tv_edit;
    private CheckBox cb_select;

    @Override
    public void setOnNotifyDataChangeListener(OnNotifyDataChangeListener listener) {
        mListener = listener;
    }

    @Override
    public View createView(Context contex) {
        return null;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent, boolean attachToRoot) {
        View convertView = inflater.inflate(R.layout.item_common_header, parent, false);
        tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
        cb_select = convertView.findViewById(R.id.cb_shop_check);
        tv_edit = convertView.findViewById(R.id.tv_shop_opt);
        return convertView;
    }

    @Override
    public void bindView(Context context, final ShopObservable bean) {
        if (bean == null) {
            return;
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
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setEditingChange(!bean.isEditing);
                tv_edit.setText(bean.isEditing ? "完成" : "编辑");
                if (mListener != null) {
                    mListener.shouldUpdateData();
                }
            }
        });
        if(bean.isEditing) {
            tv_edit.setText("完成");
        }else {
            tv_edit.setText("编辑");
        }
        cb_select.setChecked(bean.isChecked);
        tv_shop_name.setText(bean.shopObservableSrc.shopName);
    }

    @Override
    public void bindView(Context context, ShopObservable bean, int position) {

    }
}
