package com.example.snowson.apptest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.CartGoodsObservable;
import com.example.snowson.apptest.bean.ShopObservable;

import java.util.List;

/**
 * author: snowson
 * created on: 18-1-4 上午12:13
 * description:
 */

public class CartExpandAdapter extends BaseExpandableListAdapter {

    private List<ShopObservable> mCartData;
    private LayoutInflater mLayoutInflater;

    public CartExpandAdapter(List<ShopObservable> cartData, Context context) {
        this.mCartData = cartData;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mCartData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCartData.get(groupPosition).shopObservableSrc.obsCartGoods.size();
    }

    @Override
    public ShopObservable getGroup(int groupPosition) {
        return mCartData.get(groupPosition);
    }

    @Override
    public CartGoodsObservable getChild(int groupPosition, int childPosition) {
        return mCartData.get(groupPosition).shopObservableSrc.obsCartGoods.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView,
                             final ViewGroup parent) {
        GroupViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new GroupViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_xlist_header_child,
                    parent, false);
            viewHolder.tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
            viewHolder.cb_select = convertView.findViewById(R.id.cb_shop_check);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        ShopObservable shopObservable = getGroup(groupPosition);
        viewHolder.tv_shop_name.setText(shopObservable.shopObservableSrc.shopName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ChildViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_xlist_body_child,
                    parent, false);
            viewHolder.tv_goods_name = convertView.findViewById(R.id.tv_goods_name);
            viewHolder.tv_goods_type = convertView.findViewById(R.id.tv_goods_type);
            viewHolder.tv_goods_count = convertView.findViewById(R.id.tv_goods_count);
            viewHolder.tv_goods_type_edit = convertView.findViewById(R.id.tv_goods_type_edit);
            viewHolder.tv_goods_count_edit = convertView.findViewById(R.id.tv_goods_count_edit);
            viewHolder.cb_select = convertView.findViewById(R.id.cb_goods_check);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder) convertView.getTag();
        }
        View vs_footer = convertView.findViewById(R.id.vs_footer);
        if (isLastChild) {
            vs_footer.setVisibility(View.VISIBLE);
        } else {
            vs_footer.setVisibility(View.GONE);
        }
        final CartGoodsObservable obsCartGoods = getChild(groupPosition, childPosition);
        viewHolder.tv_goods_name.setText(obsCartGoods.cartGoodsBean.goodsName);
        viewHolder.tv_goods_type.setText(obsCartGoods.cartGoodsBean.goodsType);
        viewHolder.tv_goods_count.setText(String.valueOf(obsCartGoods.cartGoodsBean.goodsCount));
        viewHolder.tv_goods_count_edit.setText(String.valueOf(obsCartGoods.cartGoodsBean.goodsCount));
        viewHolder.tv_goods_type_edit.setText(obsCartGoods.cartGoodsBean.goodsType);
        viewHolder.cb_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                obsCartGoods.setCheckedChange(isChecked);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        TextView tv_shop_name;
        CheckBox cb_select;
    }

    class ChildViewHolder {
        TextView tv_goods_name;
        TextView tv_goods_type;
        TextView tv_goods_count;
        TextView tv_goods_type_edit;
        TextView tv_goods_count_edit;
        CheckBox cb_select;
    }
}
