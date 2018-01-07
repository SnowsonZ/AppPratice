package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.CartGoodsBean;
import com.example.snowson.apptest.bean.CartGoodsObservable;

/**
 * author: snowson
 * created on: 18-1-6 下午2:20
 * description:
 */

public class CartBodyViewHolder extends TypeHolder<CartGoodsObservable>
        implements View.OnClickListener{
    public static final int MAX_COUNT = 5;
    public static final int MIN_COUNT = 1;
    private TextView tv_goods_name;
    private TextView tv_goods_type;
    private TextView tv_goods_count;
    private TextView tv_goods_type_edit;
    private TextView tv_goods_count_edit;
    private CheckBox cb_select;
    private RelativeLayout rlayout_display;
    private View rlayout_edit;
    private TextView tv_goods_add;
    private TextView tv_goods_sub;
    private TextView tv_goods_delete;
    private CartGoodsObservable mData;
    private Context mContext;

    @Override
    public View createView(Context contex) {
        return null;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent, boolean attachToRoot) {
        View convertView = inflater.inflate(R.layout.item_cart_with_footer,
                parent, false);
        tv_goods_name = convertView.findViewById(R.id.tv_goods_name);
        tv_goods_type = convertView.findViewById(R.id.tv_goods_type);
        tv_goods_count = convertView.findViewById(R.id.tv_goods_count);
        tv_goods_type_edit = convertView.findViewById(R.id.tv_goods_type_edit);
        tv_goods_count_edit = convertView.findViewById(R.id.tv_goods_count_edit);
        cb_select = convertView.findViewById(R.id.cb_goods_check);
        tv_goods_add = convertView.findViewById(R.id.tv_goods_add);
        tv_goods_sub = convertView.findViewById(R.id.tv_goods_sub);
        tv_goods_delete = convertView.findViewById(R.id.tv_goods_delete);
        rlayout_edit = convertView.findViewById(R.id.rlayout_edit);
        rlayout_display = convertView.findViewById(R.id.rlayout_display);
        convertView.setTag(this);

        return convertView;
    }

    @Override
    public void bindView(Context context, final CartGoodsObservable bean) {
        if (bean == null) {
            return;
        }
        mContext = context;
        mData = bean;
        CartGoodsBean cartGoodsBean = bean.cartGoodsBean;
        tv_goods_name.setText(cartGoodsBean.goodsName);
        tv_goods_type.setText(cartGoodsBean.goodsType);
        tv_goods_count.setText(String.valueOf(cartGoodsBean.goodsCount));

        if (bean.isEditing) {
            rlayout_display.setVisibility(View.GONE);
            rlayout_edit.setVisibility(View.VISIBLE);
        } else {
            rlayout_edit.setVisibility(View.GONE);
            rlayout_display.setVisibility(View.VISIBLE);
        }

        cb_select.setOnClickListener(this);
        tv_goods_add.setOnClickListener(this);
        tv_goods_count_edit.setOnClickListener(this);
        tv_goods_sub.setOnClickListener(this);
        tv_goods_type_edit.setOnClickListener(this);
        tv_goods_delete.setOnClickListener(this);
        cb_select.setChecked(bean.isChecked);
        tv_goods_name.setText(cartGoodsBean.goodsName);
        tv_goods_type.setText(cartGoodsBean.goodsType);
        tv_goods_count.setText(String.valueOf(cartGoodsBean.goodsCount));
        tv_goods_count_edit.setText(String.valueOf(cartGoodsBean.goodsCount));
        tv_goods_type_edit.setText(cartGoodsBean.goodsType);
        Integer count = Integer.valueOf(tv_goods_count_edit.getText().toString());
        changeTextColor(count);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb_goods_check:
                mData.setCheckedChange(cb_select.isChecked());
                if (mListener != null) {
                    mListener.shouldUpdateData();
                }
                break;
            case R.id.tv_goods_add:
                Integer count = Integer.valueOf(tv_goods_count_edit.getText().toString());
                //此时监测库存是否充足
                if(count >= MAX_COUNT) {
                    Toast.makeText(mContext, "已达购买上限", Toast.LENGTH_SHORT).show();
                    return;
                }
                tv_goods_count_edit.setText(String.valueOf(++count));
                changeTextColor(count);
                break;
            case R.id.tv_goods_count_edit:
                Toast.makeText(mContext, "弹出填写数量对话框", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_sub:
                Integer count1 = Integer.valueOf(tv_goods_count_edit.getText().toString());
                //此时监测库存是否充足
                if(count1 <= MIN_COUNT) {
                    Toast.makeText(mContext, "已达购买下限", Toast.LENGTH_SHORT).show();
                    return;
                }
                tv_goods_count_edit.setText(String.valueOf(--count1));
                changeTextColor(count1);
                break;
            case R.id.tv_goods_type_edit:
                Toast.makeText(mContext, "弹出修改商品规格对话框", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_goods_delete:
                //从购物车中删除该商品
                if(mListener != null) {
                    mListener.deleteGoods(mData);
                }
                break;
            default:
                break;
        }
    }

    private void changeTextColor(int count) {
        if(count >= MAX_COUNT) {
            tv_goods_add.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
        }else if(count <= MIN_COUNT) {
            tv_goods_sub.setTextColor(mContext.getResources().getColor(android.R.color.darker_gray));
        }else {
            tv_goods_sub.setTextColor(mContext.getResources().getColor(android.R.color.black));
            tv_goods_add.setTextColor(mContext.getResources().getColor(android.R.color.black));
        }
    }
}
