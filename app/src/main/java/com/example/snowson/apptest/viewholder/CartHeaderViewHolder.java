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
import com.example.snowson.apptest.utils.TypeData;

/**
 * author: snowson
 * created on: 18-1-6 下午7:02
 * description:
 */

public class CartHeaderViewHolder extends BaseHeaderViewHolder {

    private TextView tv_shop_name;
    private TextView tv_edit;
    private CheckBox cb_select;

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
    public void bindView(Context context, final TypeData bean) {
        if (bean == null) {
            return;
        }
        final ShopObservable result = (ShopObservable) bean;
        cb_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setCheckedChange(cb_select.isChecked());
                if (mListener != null) {
                    mListener.shouldUpdateData();
                }
            }
        });
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setEditingChange(!result.isEditing);
                tv_edit.setText(result.isEditing ? "完成" : "编辑");
                if (mListener != null) {
                    mListener.shouldUpdateData();
                }
            }
        });
        cb_select.setChecked(result.isChecked);
        tv_shop_name.setText(result.shopObservableSrc.shopName);
    }
}
