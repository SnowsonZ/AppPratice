package com.example.snowson.apptest.bean.dagger2;

import javax.inject.Inject;

/**
 * Created by snowson on 18-2-5.
 */

public class CartBean {

    @Inject
    GoodsBean gBean;
    String cartId;
    private int count;
    private float totalPrice;

    @Inject
    public CartBean() {
        cartId = "该购物车ID";
    }

    public CartBean(String cartId, int count, float totalPrice) {
        this.cartId = cartId;
        this.count = count;
        this.totalPrice = totalPrice;
    }

    public GoodsBean getgBean() {
        return gBean;
    }

    public void setgBean(GoodsBean gBean) {
        this.gBean = gBean;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
