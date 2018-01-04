package com.example.snowson.apptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.adapter.CartExpandAdapter;
import com.example.snowson.apptest.bean.ShopBean;
import com.example.snowson.apptest.bean.CartGoodsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author: snowson
 * created on: 18-1-4 上午1:02
 * description:
 */

public class PageCartFragment extends BaseFragment {

    private ExpandableListView mCartElv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mCartElv = (ExpandableListView) inflater.inflate(R.layout.fragment_cart,
                container, false);
        initView();
        initData();
        return mCartElv;
    }

    private void initView() {
        mCartElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
    }

    private void initData() {
        List<ShopBean> cartBeans = new ArrayList<ShopBean>();
        for (int i = 0; i < 15; i++) {
            ShopBean cartBean = new ShopBean();
            cartBean.shopName = "森马旗舰店" + i;
            List<CartGoodsBean> goodsBeans = new ArrayList<CartGoodsBean>();
            int goodsCount = (int) (Math.random() * 5) + 1;
            for (int j = 0; j < goodsCount; j++) {
                CartGoodsBean goodsBean = new CartGoodsBean();
                goodsBean.goodsName = cartBean.shopName + " 服装 " + j;
                goodsBean.goodsCount = (int) (Math.random() * 3) + 1;
                goodsBean.goodsType = "黑色 XL";
                goodsBeans.add(goodsBean);
            }
            cartBean.goodsInfo = goodsBeans;
            cartBeans.add(cartBean);
        }
        CartExpandAdapter adapter = new CartExpandAdapter(cartBeans, getActivity());
        mCartElv.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            mCartElv.expandGroup(i);
        }
    }
}
