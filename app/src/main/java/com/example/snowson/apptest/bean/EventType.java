package com.example.snowson.apptest.bean;

/**
 * Created by snowson on 18-1-5.
 */

public class EventType<T> {
    public int type;
    public T value;

    public static final int TYPE_ALL = 0;
    public static final int TYPE_GROUP_CHECK = 1;
    public static final int TYPE_CHILD = 2;
    public static final int TYPE_GROUP_EDITING = 3;
}
