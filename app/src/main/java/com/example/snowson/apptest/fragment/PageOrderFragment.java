package com.example.snowson.apptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.adapter.CommonExpandAdapter;
import com.example.snowson.apptest.bean.GoodsBean;
import com.example.snowson.apptest.bean.OrderBean;
import com.example.snowson.apptest.bean.OrderItemBean;
import com.example.snowson.apptest.viewholder.BaseHeaderViewHolder;
import com.example.snowson.apptest.viewholder.OrderBodyViewHolder;
import com.example.snowson.apptest.viewholder.OrderHeaderViewHolder;
import com.example.snowson.apptest.viewholder.TypeHolder;
import com.example.snowson.apptest.viewholder.ViewHolderCreator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author: snowson
 * created on: 18-1-4 上午1:02
 * description:
 */

public class PageOrderFragment extends BaseFragment implements View.OnClickListener {

    private ExpandableListView mOrderELv;
    private List<OrderBean> orderList;
    private CommonExpandAdapter mAdapter;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_order, container, false);
            initView(mRootView);
            initData();
            initEvent();
        }
        return mRootView;
    }

    private void initView(View container) {
        mOrderELv = container.findViewById(R.id.elv_order);
    }

    @SuppressWarnings("unchecked")
    private void initData() {
        orderList = new ArrayList<OrderBean>();
        for (int i = 0; i < 4; i++) {
            OrderBean orderBean = new OrderBean();
            orderBean.shopName = "森马旗舰店" + i;
            orderBean.status = (int) (Math.random() * 5);
            List<OrderItemBean> orderItems = new ArrayList<OrderItemBean>();
            int goodsCount = (int) (Math.random() * 3) + 1;
            for (int j = 0; j < goodsCount; j++) {
                OrderItemBean orderItem = new OrderItemBean();
                orderItem.status = (int) (Math.random() * 5);
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.goodsName = orderBean.shopName + " 服装 " + j;
                goodsBean.goodsCount = (int) (Math.random() * 3) + 1;
                goodsBean.goodsType = "黑色 XL";
                DecimalFormat decimalFormat = new DecimalFormat(".00");
                goodsBean.goodsUnitPrice = Float.parseFloat(
                        decimalFormat.format((float) (Math.random() * 100) + 100));
                orderItem.goodsBean = goodsBean;
                orderItems.add(orderItem);
            }
            orderBean.itemBeans = orderItems;
            orderList.add(orderBean);
        }
        mAdapter = new CommonExpandAdapter(orderList, getActivity(),
                new ViewHolderCreator<TypeHolder>() {
                    @Override
                    public TypeHolder createHolder() {
                        return new OrderBodyViewHolder();
                    }
                }, new ViewHolderCreator<BaseHeaderViewHolder>() {
            @Override
            public BaseHeaderViewHolder createHolder() {
                return new OrderHeaderViewHolder();
            }
        });
        mOrderELv.setAdapter(mAdapter);
        for (int i = 0; i < mAdapter.getGroupCount(); i++) {
            mOrderELv.expandGroup(i);
        }
    }

    private void initEvent() {
        mOrderELv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                Toast.makeText(getActivity(), "跳转到店铺页面", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        mOrderELv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                Toast.makeText(getActivity(), "跳转订单详情页", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }
}
