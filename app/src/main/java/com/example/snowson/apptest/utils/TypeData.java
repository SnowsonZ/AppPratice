package com.example.snowson.apptest.utils;

/**
 * author: snowson
 * created on: 18-1-6 下午6:38
 * description:
 */

public interface TypeData<T> {
    T getChild(int childPosition);
    int getChildCount();
}
