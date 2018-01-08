package com.example.snowson.apptest.bean;

/**
 * Created by snowson on 18-1-5.
 */

public class EventType<T> {
    public int type;
    public String typeName;
    public T value;

    public static final int TYPE_SELECTED = 0;
    public static final int TYPE_EDITING = 1;
    public static final int TYPE_CHECK_ALL = 2;
}
