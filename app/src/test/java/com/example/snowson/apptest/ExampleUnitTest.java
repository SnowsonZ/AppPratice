package com.example.snowson.apptest;

import com.example.snowson.apptest.activity.MapTestActivity;
import com.example.snowson.apptest.utils.MapUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.annotation.Config.TARGET_SDK;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(libraries = {
        "libs/umeng_social_api.jar",
        "libs/umeng_social_net.jar",
        "libs/umeng_social_shareboard.jar",
        "libs/umeng_social_tool.jar"
})
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void mapTest() {
        int count = 10000;
        int key = 423;
        MapUtils.arrayMap(count, key);
        MapUtils.parseArray(count, key);
        MapUtils.hashMap(count, key);
    }
}