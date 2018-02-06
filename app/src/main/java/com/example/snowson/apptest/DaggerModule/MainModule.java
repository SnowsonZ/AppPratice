package com.example.snowson.apptest.DaggerModule;

import android.content.Context;

import com.example.snowson.apptest.bean.dagger2.CartBean;
import com.example.snowson.apptest.inter.Level;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by snowson on 18-2-5.
 */
@Module
public class MainModule {

    Context mContext;

    public MainModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Level("LOW")
    public CartBean provideCart(String cartId, int count, float totalPrice) {
        return new CartBean(cartId, count, totalPrice);
    }

    @Provides
    @Level("HIGH")
    @Singleton
    public CartBean provideCartHigh() {
        return new CartBean("MainModule Create High", 12, 10.00f);
    }

    @Provides
    public String provideCartId() {
        return "MainModule Create LOW";
    }

    @Provides
    public int provideCount() {
        return 10;
    }

    @Provides
    public float provideTotalPrice() {
        return 13.23f;
    }


}
