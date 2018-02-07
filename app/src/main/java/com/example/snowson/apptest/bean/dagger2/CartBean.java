package com.example.snowson.apptest.bean.dagger2;

import javax.inject.Inject;

/**
 * Created by snowson on 18-2-5.
 */

public class CartBean {

    GoodsBean gBean;

    @Inject
    public CartBean(GoodsBean gBean) {
        this.gBean = gBean;
    }

    public GoodsBean getgBean() {
        return gBean;
    }

    public void setgBean(GoodsBean gBean) {
        this.gBean = gBean;
    }
}
