package com.example.snowson.apptest.utils;

import com.amap.api.location.DPoint;

import org.junit.Test;

/**
 * Created by snowson on 17-12-12.
 */
public class DistanceUtilsTest {

    @Test
    public void caculateDistance() {
        DPoint startP = new DPoint(39.9028960000, 116.4279200000);
        DPoint endP = new DPoint(31.1960990000, 121.3397660000);
        org.junit.Assert.assertEquals(0, DistanceUtils.caculateDistance(startP, endP), 0);
    }

}