package com.example.snowson.apptest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.CartBean;
import com.example.snowson.apptest.bean.CartGoodsBean;

import java.util.List;

/**
 * author: snowson
 * created on: 18-1-4 上午12:13
 * description:
 */

public class CartExpandAdapter extends BaseExpandableListAdapter {

    private List<CartBean> mCartData;
    private LayoutInflater mLayoutInflater;

    public CartExpandAdapter(List<CartBean> cartData, Context context) {
        this.mCartData = cartData;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mCartData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCartData.get(groupPosition).goodsInfo.size();
    }

    @Override
    public CartBean getGroup(int groupPosition) {
        return mCartData.get(groupPosition);
    }

    @Override
    public CartGoodsBean getChild(int groupPosition, int childPosition) {
        return mCartData.get(groupPosition).goodsInfo.get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new GroupViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_xlist_header_child,
                    parent, false);
            viewHolder.tv_shop_name = convertView.findViewById(R.id.tv_shop_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        CartBean cartBean = getGroup(groupPosition);
        viewHolder.tv_shop_name.setText(cartBean.shopName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
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
        CartGoodsBean goodsBean = getChild(groupPosition, childPosition);
        viewHolder.tv_goods_name.setText(goodsBean.goodsName);
        viewHolder.tv_goods_type.setText(goodsBean.goodsType);
        viewHolder.tv_goods_count.setText(String.valueOf(goodsBean.goodsCount));
        viewHolder.tv_goods_count_edit.setText(String.valueOf(goodsBean.goodsCount));
        viewHolder.tv_goods_type_edit.setText(goodsBean.goodsType);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        TextView tv_shop_name;
    }

    class ChildViewHolder {
        TextView tv_goods_name;
        TextView tv_goods_type;
        TextView tv_goods_count;
        TextView tv_goods_type_edit;
        TextView tv_goods_count_edit;
    }
}
