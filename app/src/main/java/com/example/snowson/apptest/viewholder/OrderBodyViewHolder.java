package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.OrderBean;
import com.example.snowson.apptest.bean.OrderItemBean;
import com.example.snowson.apptest.bean.OrderStatus;
import com.example.snowson.apptest.utils.ScreenUtils;

/**
 * author: snowson
 * created on: 18-1-6 下午2:20
 * description: 订单body组件ViewHolder
 */

public class OrderBodyViewHolder extends TypeHolder<OrderBean> implements View.OnClickListener{

    private TextView mGoodsNameTv;
    private TextView mGoodsTypeTv;
    private TextView mGoodsCountTv;
    private TextView mGoodsUnitPriceTv;
    private TextView mOrderPayTv;
    private TextView mOrderCancleTv;
    private TextView mOrderGettedTv;
    private TextView mGoodsLocationTv;
    private TextView mOrderComment;
    private TextView mOrderCancelDetail;
    private TextView mAmountCount;
    private TextView mAmountPrice;
    private TextView mOrderItemCancelTv;
    private Context mContext;


    @Override
    public View createView(Context contex) {
        return null;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent, boolean attachToRoot) {
        View convertView = inflater.inflate(R.layout.item_order_with_footer,
                parent, false);
        mGoodsNameTv = convertView.findViewById(R.id.tv_goods_name);
        mGoodsTypeTv = convertView.findViewById(R.id.tv_goods_type);
        mGoodsCountTv = convertView.findViewById(R.id.tv_goods_count);
        mGoodsUnitPriceTv = convertView.findViewById(R.id.tv_goods_price);
        mOrderPayTv = convertView.findViewById(R.id.tv_order_pay);
        mOrderCancleTv = convertView.findViewById(R.id.tv_order_cancel);
        mOrderGettedTv = convertView.findViewById(R.id.tv_goods_get);
        mGoodsLocationTv = convertView.findViewById(R.id.tv_current_location);
        mOrderItemCancelTv = convertView.findViewById(R.id.tv_order_item_cancel);
        mOrderComment = convertView.findViewById(R.id.tv_order_comment);
        mOrderCancelDetail = convertView.findViewById(R.id.tv_order_cancel_detail);
        mAmountCount = convertView.findViewById(R.id.tv_goods_count_amount);
        mAmountPrice = convertView.findViewById(R.id.tv_price_amount);
        return convertView;
    }


    @Override
    public void bindView(Context context, OrderBean bean) {

    }

    @Override
    public void bindView(Context context, OrderBean bean, int position) {
        if (bean == null || context == null) {
            return;
        }
        mContext = context;
        mOrderPayTv.setOnClickListener(this);
        mOrderCancleTv.setOnClickListener(this);
        mOrderCancelDetail.setOnClickListener(this);
        mOrderComment.setOnClickListener(this);
        mOrderGettedTv.setOnClickListener(this);
        mGoodsLocationTv.setOnClickListener(this);
        mOrderItemCancelTv.setOnClickListener(this);
        //设置footer状态
        switch (bean.status) {
            case OrderStatus.STATUS_UNPAY:
                mOrderPayTv.setVisibility(View.VISIBLE);
                mOrderCancleTv.setVisibility(View.VISIBLE);
                mOrderGettedTv.setVisibility(View.GONE);
                mGoodsLocationTv.setVisibility(View.GONE);
                mOrderComment.setVisibility(View.GONE);
                mOrderCancelDetail.setVisibility(View.GONE);
                break;
            case OrderStatus.STATUS_UNPASS:
                mOrderCancleTv.setVisibility(View.VISIBLE);
                mOrderGettedTv.setVisibility(View.GONE);
                mGoodsLocationTv.setVisibility(View.GONE);
                mOrderPayTv.setVisibility(View.GONE);
                mOrderComment.setVisibility(View.GONE);
                mOrderCancelDetail.setVisibility(View.GONE);
                break;
            case OrderStatus.STATUS_UNGETTED:
                mOrderGettedTv.setVisibility(View.VISIBLE);
                mGoodsLocationTv.setVisibility(View.VISIBLE);
                mOrderPayTv.setVisibility(View.GONE);
                mOrderCancleTv.setVisibility(View.GONE);
                mOrderComment.setVisibility(View.GONE);
                mOrderCancelDetail.setVisibility(View.GONE);
                break;
            case OrderStatus.STATUS_UNCOMMENT:
                mOrderComment.setVisibility(View.VISIBLE);
                mOrderGettedTv.setVisibility(View.GONE);
                mGoodsLocationTv.setVisibility(View.GONE);
                mOrderPayTv.setVisibility(View.GONE);
                mOrderCancleTv.setVisibility(View.GONE);
                mOrderCancelDetail.setVisibility(View.GONE);
                break;
            case OrderStatus.STATUS_CANCEL:
                mOrderCancelDetail.setVisibility(View.VISIBLE);
                mOrderComment.setVisibility(View.GONE);
                mOrderGettedTv.setVisibility(View.GONE);
                mGoodsLocationTv.setVisibility(View.GONE);
                mOrderPayTv.setVisibility(View.GONE);
                mOrderCancleTv.setVisibility(View.GONE);
                break;
        }
        float amoutPrice = 0;
        int amoutCount = 0;
        for (int i = 0; i < bean.getChildCount(); i++) {
            OrderItemBean goods = bean.getChild(i);
            amoutPrice += goods.goodsBean.goodsUnitPrice * goods.goodsBean.goodsCount;
            amoutCount += goods.goodsBean.goodsCount;
        }
        mAmountCount.setText(String.format(context.getResources()
                .getString(R.string.goods_count_order), amoutCount));
        mAmountPrice.setText(ScreenUtils.getFormatText(String.format(context.getResources()
                .getString(R.string.price_format), amoutPrice)));

        OrderItemBean orderItem = bean.getChild(position);
        mGoodsNameTv.setText(orderItem.goodsBean.goodsName);
        mGoodsTypeTv.setText(orderItem.goodsBean.goodsType);
        mGoodsCountTv.setText(String.format(context.getResources().getString(R.string.goods_count),
                orderItem.goodsBean.goodsCount));
        mGoodsUnitPriceTv.setText(ScreenUtils.getFormatText(String.format(context.getResources()
                .getString(R.string.price_format), orderItem.goodsBean.goodsUnitPrice)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_order_pay:
                ScreenUtils.showToast(mContext, "跳转支付页面");
                break;
            case R.id.tv_order_cancel:
                ScreenUtils.showToast(mContext, "取消订单");
                break;
            case R.id.tv_order_cancel_detail:
                ScreenUtils.showToast(mContext, "取消订单查看详情页");
                break;
            case R.id.tv_order_comment:
                ScreenUtils.showToast(mContext, "跳转评价页面");
                break;
            case R.id.tv_order_item_cancel:
                ScreenUtils.showToast(mContext, "取消某单一商品");
                break;
            case R.id.tv_current_location:
                ScreenUtils.showToast(mContext, "跳转物流详情页");
                break;
            case R.id.tv_goods_get:
                ScreenUtils.showToast(mContext, "确认收货");
                break;
        }
    }
}
