package com.example.snowson.apptest.bean;

import com.example.snowson.apptest.utils.TypeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snowson on 18-1-10.
 * 订单店铺原始类
 */

public class OrderBean implements TypeData<OrderItemBean> {
    //该订单状态
    public int status;
    public String shopName;
    public List<OrderItemBean> itemBeans = new ArrayList<OrderItemBean>();

    @Override
    public OrderItemBean getChild(int childPosition) {
        return itemBeans.get(childPosition);
    }

    @Override
    public int getChildCount() {
        return itemBeans.size();
    }
}
