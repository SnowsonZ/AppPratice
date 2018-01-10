package com.example.snowson.apptest.bean;

/**
 * Created by snowson on 18-1-10.
 */

public class OrderStatus {
    public static final int STATUS_UNPAY = 0; //等待买家付款
    public static final int STATUS_UNPASS = 1; //买家已付款
    public static final int STATUS_UNGETTED = 2; //卖家已发货
    public static final int STATUS_UNCOMMENT = 3; //交易成功
    public static final int STATUS_CANCEL = 4; //无提示信息
}
