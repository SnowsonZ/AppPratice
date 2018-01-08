package com.example.snowson.apptest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.snowson.apptest.OnNotifyDataChangeListener;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.CartGoodsObservable;
import com.example.snowson.apptest.bean.ShopObservable;
import com.example.snowson.apptest.utils.TypeData;
import com.example.snowson.apptest.viewholder.BaseHeaderViewHolder;
import com.example.snowson.apptest.viewholder.CartHeaderViewHolder;
import com.example.snowson.apptest.viewholder.TypeHolder;
import com.example.snowson.apptest.viewholder.ViewHolderCreator;

import java.util.List;

/**
 * author: snowson
 * created on: 18-1-4 上午12:13
 * description:
 */

public class CommonExpandAdapter<T, K> extends BaseExpandableListAdapter
        implements OnNotifyDataChangeListener {

    private List<TypeData<K>> mCartData;
    private LayoutInflater mLayoutInflater;
    private ViewHolderCreator<TypeHolder<T>> mBodyCreator;
    private ViewHolderCreator<BaseHeaderViewHolder> mHeaderCreator;

    public CommonExpandAdapter(List<TypeData<K>> cartData, Context context,
                               ViewHolderCreator<TypeHolder<T>> bodyCreator,
                               ViewHolderCreator<BaseHeaderViewHolder> headerCreator) {
        this.mCartData = cartData;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mBodyCreator = bodyCreator;
        this.mHeaderCreator = headerCreator;
    }

    @Override
    public int getGroupCount() {
        return mCartData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCartData.get(groupPosition).getChildCount();
    }

    @Override
    public TypeData getGroup(int groupPosition) {
        return mCartData.get(groupPosition);
    }

    @Override
    public K getChild(int groupPosition, int childPosition) {
        return mCartData.get(groupPosition).getChild(childPosition);
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

    @SuppressWarnings("unchecked")
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView,
                             final ViewGroup parent) {
        BaseHeaderViewHolder holder;
        if (convertView == null) {
            holder = mHeaderCreator.createHolder();
            convertView = holder.createView(mLayoutInflater, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (CartHeaderViewHolder) convertView.getTag();
        }
        holder.setOnNotifyDataChangeListener(this);
        //控制显示组间距
        if (groupPosition == 0) {
            convertView.findViewById(R.id.v_header_divider).setVisibility(View.GONE);
        } else {
            convertView.findViewById(R.id.v_header_divider).setVisibility(View.VISIBLE);
        }

        TypeData groupBean = getGroup(groupPosition);
        holder.bindView(parent.getContext(), groupBean);
        return convertView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        TypeHolder holder;
        if (convertView == null) {
            holder = mBodyCreator.createHolder();
            convertView = holder.createView(mLayoutInflater, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (TypeHolder) convertView.getTag();
        }
        holder.setOnNotifyDataChangeListener(this);
        //显示footer
        View vs_footer = convertView.findViewById(R.id.vs_footer);
        if (isLastChild) {
            vs_footer.setVisibility(View.VISIBLE);
        } else {
            vs_footer.setVisibility(View.GONE);
        }

        K child = getChild(groupPosition, childPosition);
        holder.bindView(parent.getContext(), child);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void shouldUpdateData() {
        notifyDataSetChanged();
    }

    @Override
    public void deleteGoods(CartGoodsObservable bean) {
        for (int i = 0; i < getGroupCount(); i++) {
            ShopObservable obsShop = (ShopObservable) mCartData.get(i);
            obsShop.shopObservableSrc.obsCartGoods.remove(bean);
            if(obsShop.getChildCount() <= 0) {
                mCartData.remove(obsShop);
            }
        }
        notifyDataSetChanged();
        bean.setCheckedChange(bean.isChecked);
    }
}
