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
import com.example.snowson.apptest.utils.TypeData;

/**
 * 订单footer组件ViewHolder
 * Created by snowson on 18-1-10.
 */

@Deprecated
public class OrderFooterViewHolder extends BaseHeaderViewHolder {
    private TextView mOrderPayTv;
    private TextView mOrderCancleTv;
    private TextView mOrderGettedTv;
    private TextView mGoodsLocationTv;
    private TextView mOrderComment;
    private TextView mOrderCancelDetail;
    private TextView mAmountCount;
    private TextView mAmountPrice;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent, boolean attachToRoot) {

        View convertView = inflater.inflate(R.layout.item_order_footer,
                parent, false);
        mOrderPayTv = convertView.findViewById(R.id.tv_order_pay);
        mOrderCancleTv = convertView.findViewById(R.id.tv_order_cancel);
        mOrderGettedTv = convertView.findViewById(R.id.tv_goods_get);
        mGoodsLocationTv = convertView.findViewById(R.id.tv_current_location);
        mOrderComment = convertView.findViewById(R.id.tv_order_comment);
        mOrderCancelDetail = convertView.findViewById(R.id.tv_order_cancel_detail);
        mAmountCount = convertView.findViewById(R.id.tv_goods_count_amount);
        mAmountPrice = convertView.findViewById(R.id.tv_price_amount);

        return super.createView(inflater, parent, attachToRoot);
    }

    @Override
    public void bindView(Context context, TypeData bean) {
        if (bean == null) {
            return;
        }
        //根据订单状态显示对应操作按钮
        OrderBean order = (OrderBean) bean;
        switch (order.status) {
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
        mAmountCount.setText(String.format(context.getResources()
                .getString(R.string.goods_count_order), order.getChildCount()));
        float amoutPrice = 0;
        for (int i = 0; i < order.getChildCount(); i++) {
            OrderItemBean goods = order.getChild(i);
            amoutPrice += goods.goodsBean.goodsUnitPrice * goods.goodsBean.goodsCount;
        }
        mAmountPrice.setText(ScreenUtils.getFormatText(String.format(context.getResources()
                .getString(R.string.price_format), amoutPrice)));
    }
}
