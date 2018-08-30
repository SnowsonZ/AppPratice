package com.example.java.multiextends;

/**
 * @Author: Snowson
 * @Date: 2018/8/30 11:14
 * @Description:
 */
public interface DefaultFeature2 {

    default void fun1(long a) {

    }

    static void fun2() {

    }

    default void fun3(){}
}
