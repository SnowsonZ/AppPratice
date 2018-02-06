package com.example.snowson.apptest.bean.dagger2;

import javax.inject.Inject;

/**
 * Created by snowson on 18-2-5.
 */

public class GoodsBean {

    private String goodsName;
    private float unitPrice;
    private String type;

    @Inject
    GoodsBean() {

    }


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
