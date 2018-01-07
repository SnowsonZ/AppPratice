package com.example.snowson.apptest;

import com.example.snowson.apptest.bean.CartGoodsObservable;

/**
 * author: snowson
 * created on: 18-1-6 下午8:28
 * description:
 */

public interface OnNotifyDataChangeListener {
    void shouldUpdateData();

    void deleteGoods(CartGoodsObservable bean);
}
