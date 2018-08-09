package com.example.snowson.apptest.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;

public class JsonUtil {
    public static Gson instance = getInstance();

    private static Gson getInstance() {
        if (instance == null) {
            synchronized (JsonUtil.class) {
                if (instance == null) {
                    instance = new Gson();
                }
            }
        }
        return instance;
    }

    public static <T> T fromJsonReader(Reader reader) {
        return instance.fromJson(reader, new TypeToken<T>() {
        }.getType());
    }
}
