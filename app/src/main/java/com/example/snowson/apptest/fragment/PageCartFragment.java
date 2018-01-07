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
import com.example.snowson.apptest.bean.CartGoodsObservable;
import com.example.snowson.apptest.bean.ShopObservable;
import com.example.snowson.apptest.viewholder.BaseHeaderViewHolder;
import com.example.snowson.apptest.viewholder.CartBodyViewHolder;
import com.example.snowson.apptest.viewholder.CartHeaderViewHolder;
import com.example.snowson.apptest.viewholder.TypeHolder;
import com.example.snowson.apptest.viewholder.ViewHolderCreator;

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
                Toast.makeText(getActivity(), "跳转到店铺页面", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        mCartElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                Toast.makeText(getActivity(), "跳转到商品详情页", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initData() {
        List<ShopObservable> obsCart = new ArrayList<ShopObservable>();
        for (int i = 0; i < 15; i++) {
            ShopObservable obsShop = new ShopObservable();
            obsShop.isChecked = false;
            obsShop.isEditing = false;
            obsShop.shopObservableSrc.shopName = "森马旗舰店" + i;
            List<CartGoodsObservable> obsGoods = new ArrayList<CartGoodsObservable>();
            int goodsCount = (int) (Math.random() * 5) + 1;
            for (int j = 0; j < goodsCount; j++) {
                CartGoodsObservable obsGood = new CartGoodsObservable();
                obsGood.isChecked = false;
                obsGood.isEditing = false;
                obsGood.cartGoodsBean.goodsName = obsShop.shopObservableSrc.shopName + " 服装 " + j;
                obsGood.cartGoodsBean.goodsCount = (int) (Math.random() * 3) + 1;
                obsGood.cartGoodsBean.goodsType = "黑色 XL";
                obsGoods.add(obsGood);
                obsGood.addObserver(obsShop);
                obsShop.addObserver(obsGood);
            }
            obsShop.shopObservableSrc.obsCartGoods = obsGoods;
            obsCart.add(obsShop);
        }
        CommonExpandAdapter adapter = new CommonExpandAdapter(obsCart, getActivity(),
                new ViewHolderCreator<TypeHolder>() {
            @Override
            public TypeHolder createHolder() {
                return new CartBodyViewHolder();
            }
        }, new ViewHolderCreator<BaseHeaderViewHolder>() {
            @Override
            public BaseHeaderViewHolder createHolder() {
                return new CartHeaderViewHolder();
            }
        });
        mCartElv.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            mCartElv.expandGroup(i);
        }
    }
}
