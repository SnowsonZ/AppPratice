package com.example.java.multiextends;

/**
 * @Author: Snowson
 * @Date: 2018/8/30 11:59
 * @Description:
 */
public interface DefaultFeatureSub extends DefaultFeature, DefaultFeature2 {
    @Override
    default void fun3() {

    }
}
