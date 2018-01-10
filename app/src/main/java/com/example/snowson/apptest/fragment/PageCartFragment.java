package com.example.snowson.apptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.adapter.CommonExpandAdapter;
import com.example.snowson.apptest.bean.CartGoodsObservable;
import com.example.snowson.apptest.bean.CartOptObservable;
import com.example.snowson.apptest.bean.GoodsBean;
import com.example.snowson.apptest.bean.ShopBean;
import com.example.snowson.apptest.bean.ShopObservable;
import com.example.snowson.apptest.utils.ScreenUtils;
import com.example.snowson.apptest.viewholder.BaseHeaderViewHolder;
import com.example.snowson.apptest.viewholder.CartBodyViewHolder;
import com.example.snowson.apptest.viewholder.CartHeaderViewHolder;
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

public class PageCartFragment extends BaseFragment implements View.OnClickListener, CartOptObservable.OnChangeOptListener {

    private ExpandableListView mCartElv;
    private TextView mPayAmountTv;
    private CheckBox mSelectAllCb;
    private TextView mPayTv;
    private View mOptAllRlayout;
    private View mPayLlayout;
    private TextView mDeleteAllTv;
    private TextView mAddFavoriteTv;
    private List<ShopObservable> mObsCart;
    private CommonExpandAdapter mAdapter;
    private CartOptObservable mCartOptObs;
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_cart, container, false);
            initView(mRootView);
            initData();
            initEvent();
        }
        return mRootView;
    }

    private void initView(View container) {
        mOptAllRlayout = container.findViewById(R.id.rlayout_opt_all);
        mPayLlayout = container.findViewById(R.id.llayout_pay);

        mCartElv = container.findViewById(R.id.elv_cart);
        mSelectAllCb = container.findViewById(R.id.cb_select_all);

        mPayAmountTv = container.findViewById(R.id.tv_payinfo_amount);
        mPayTv = container.findViewById(R.id.tv_pay);

        mDeleteAllTv = container.findViewById(R.id.tv_delete_all);
        mAddFavoriteTv = container.findViewById(R.id.tv_add_favorite);

        mPayAmountTv.setText(ScreenUtils.getFormatText("￥0.00"));
    }

    @SuppressWarnings("unchecked")
    private void initData() {
        mCartOptObs = new CartOptObservable();
        mCartOptObs.isCheacked = false;
        mObsCart = new ArrayList<ShopObservable>();
        for (int i = 0; i < 4; i++) {
            ShopObservable obsShop = new ShopObservable();
            obsShop.addObserver(mCartOptObs);
            mCartOptObs.addObserver(obsShop);
            mCartOptObs.shopObservables.add(obsShop);
            obsShop.isChecked = false;
            obsShop.isEditing = false;
            obsShop.shopObservableSrc.shopName = "森马旗舰店" + i;
            List<CartGoodsObservable> obsGoods = new ArrayList<CartGoodsObservable>();
            int goodsCount = (int) (Math.random() * 3) + 1;
            for (int j = 0; j < goodsCount; j++) {
                CartGoodsObservable obsGood = new CartGoodsObservable();
                obsGood.isChecked = false;
                obsGood.isEditing = false;
                obsGood.cartGoodsBean.goodsName = obsShop.shopObservableSrc.shopName + " 服装 " + j;
                obsGood.cartGoodsBean.goodsCount = (int) (Math.random() * 3) + 1;
                obsGood.cartGoodsBean.goodsType = "黑色 XL";
                DecimalFormat decimalFormat = new DecimalFormat(".00");
                obsGood.cartGoodsBean.goodsUnitPrice = Float.parseFloat(
                        decimalFormat.format((float) (Math.random() * 100) + 100));
                obsGoods.add(obsGood);
                obsGood.addObserver(obsShop);
                obsShop.addObserver(obsGood);
            }
            obsShop.shopObservableSrc.obsCartGoods = obsGoods;
            mObsCart.add(obsShop);
        }
        mAdapter = new CommonExpandAdapter(mObsCart, getActivity(),
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
        mCartElv.setAdapter(mAdapter);
        for (int i = 0; i < mAdapter.getGroupCount(); i++) {
            mCartElv.expandGroup(i);
        }
        //update 购物车操作栏状态
        updateCartOpt();
    }

    private void initEvent() {
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

        mSelectAllCb.setOnClickListener(this);
        mPayTv.setOnClickListener(this);
        mAddFavoriteTv.setOnClickListener(this);
        mDeleteAllTv.setOnClickListener(this);
        mCartOptObs.setOnChangeOptListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_select_all:
                mCartOptObs.setCheckedChange(mSelectAllCb.isChecked());
                mAdapter.notifyDataSetChanged();
                updateCartOpt();
                break;
            case R.id.tv_pay:
                ScreenUtils.showToast(getActivity(), "打开提交订单页面");
                //TODO
                break;
            case R.id.tv_add_favorite:
                ScreenUtils.showToast(getActivity(), "添加到收藏夹");
                break;
            case R.id.tv_delete_all:
                ScreenUtils.showToast(getActivity(), "已删除选中商品");
                break;
            default:
                break;
        }
    }

    /**
     * 获取选中的商品列表
     *
     * @return
     */
    private List<ShopBean> getSelectedGoods() {
        List<ShopBean> results = new ArrayList<ShopBean>();
        for (int i = 0; i < mObsCart.size(); i++) {
            ShopObservable shopObservable = mObsCart.get(i);
            ShopBean shopBean = null;
            //全选，该店铺所有商品
            if (shopObservable.isChecked) {
                shopBean = new ShopBean();
                for (int j = 0; j < shopObservable.shopObservableSrc.obsCartGoods.size(); j++) {
                    GoodsBean cartGoodsBean
                            = shopObservable.shopObservableSrc.obsCartGoods.get(j).cartGoodsBean;
                    shopBean.goodsInfo.add(cartGoodsBean);
                }
            } else {
                for (int j = 0; j < shopObservable.shopObservableSrc.obsCartGoods.size(); j++) {
                    if (shopObservable.shopObservableSrc.obsCartGoods.get(j).isChecked) {
                        if (shopBean == null) {
                            shopBean = new ShopBean();
                        }
                        GoodsBean cartGoodsBean = shopObservable
                                .shopObservableSrc.obsCartGoods.get(j).cartGoodsBean;
                        shopBean.goodsInfo.add(cartGoodsBean);
                    }
                }
            }
            if (shopBean != null) {
                results.add(shopBean);
            }
        }
        return results;
    }

    private void updateCartOpt() {
        float priceAmount = 0;
        List<ShopBean> selectedGoods = getSelectedGoods();
        for (int i = 0; i < selectedGoods.size(); i++) {
            ShopBean shopBean = selectedGoods.get(i);
            for (int j = 0; j < shopBean.goodsInfo.size(); j++) {
                GoodsBean cartGoodsBean = shopBean.goodsInfo.get(j);
                priceAmount += cartGoodsBean.goodsUnitPrice * cartGoodsBean.goodsCount;
            }
        }
        mPayAmountTv.setText(ScreenUtils.getFormatText(getResources()
                .getString(R.string.price_format, priceAmount)));
        //购物车为空时，全选按钮不可点击
        if(mObsCart.size() <= 0) {
            mSelectAllCb.setChecked(false);
            mSelectAllCb.setEnabled(false);
        }else {
            mSelectAllCb.setEnabled(true);
            mSelectAllCb.setChecked(mCartOptObs.isCheacked);
        }
    }

    @Override
    public void onUpdateViewState() {
        updateCartOpt();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }
}
