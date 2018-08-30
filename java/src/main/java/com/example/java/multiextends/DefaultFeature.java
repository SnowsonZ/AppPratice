package com.example.java.multiextends;

/**
 * @Author: Snowson
 * @Date: 2018/8/30 11:14
 * @Description:
 */
public interface DefaultFeature {

    default void fun1(int a) {

    }

    static void fun2() {
        System.out.println("interface static: " + DefaultFeature.class.getName());
    }

    default void fun3(){}
}
