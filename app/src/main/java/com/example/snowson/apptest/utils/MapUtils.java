package com.example.snowson.apptest.utils;

import android.annotation.TargetApi;
import android.nfc.Tag;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/3.
 */

public class MapUtils {
    private static String TAG = "MapUtils";
    @TargetApi(value = 19)
    public static void arrayMap(int count, int key) {
        long startTime = System.currentTimeMillis();
        ArrayMap<Integer, Integer> arrayMap = new ArrayMap<>();
        for (int i = 0; i < count; i++) {
            arrayMap.put(i, i);
        }
        Log.i(TAG, "arrayMap time put: " + (System.currentTimeMillis() - startTime));
        long getTime = System.currentTimeMillis();
        Integer value = arrayMap.get(key);
        Log.i(TAG, "arrayMap get Time: " + (System.currentTimeMillis() - getTime) + ", value: " + value);
        System.out.println();
        long delTime = System.currentTimeMillis();
        Integer delVal = arrayMap.remove(key);
        Log.i(TAG, "arrayMap del Time: " + (System.currentTimeMillis() - delTime) + ", value: " + delVal);
    }

    @SuppressWarnings("unchecked")
    public static void parseArray(int count, int key) {
        long startTime = System.currentTimeMillis();
        SparseArray sparseArray = new SparseArray();
        for (int i = 0; i < count; i++) {
            sparseArray.put(i, i);
        }
        Log.i(TAG, "parseArray time put: " + (System.currentTimeMillis() - startTime));

        long getTime = System.currentTimeMillis();
        Object value = sparseArray.get(key);
        Log.i(TAG, "parseArray get Time: " + (System.currentTimeMillis() - getTime) + ", value: " + value);
        long delTime = System.currentTimeMillis();
        sparseArray.remove(key);
        Log.i(TAG, "parseArray del Time: " + (System.currentTimeMillis() - delTime) + ", value: " + null);
    }

    @SuppressWarnings("unchecked")
    public static void hashMap(int count, int key) {
        long startTime = System.currentTimeMillis();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < count; i++) {
            map.put(i, i);
        }
        Log.i(TAG, "hashMap time put: " + (System.currentTimeMillis() - startTime));
        long getTime = System.currentTimeMillis();
        Object value = map.get(key);
        Log.i(TAG, "hashMap get Time: " + (System.currentTimeMillis() - getTime) + ", value: " + value);
        long delTime = System.currentTimeMillis();
        Integer delValue = map.remove(key);
        Log.i(TAG, "hashMap del Time: " + (System.currentTimeMillis() - delTime) + ", value: " + delValue);
    }
}
