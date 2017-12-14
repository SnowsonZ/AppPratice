package com.example.snowson.apptest.utils;

import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;

/**
 * Created by snowson on 17-12-12.
 */

public class DistanceUtils {

    public static float caculateDistance(DPoint startP, DPoint endP) {
        if (startP == null || endP == null) {
            return -1f;
        }
        return CoordinateConverter.calculateLineDistance(startP, endP);
    }
}
