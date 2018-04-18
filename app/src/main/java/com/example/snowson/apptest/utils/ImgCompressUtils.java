package com.example.snowson.apptest.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.snowson.apptest.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2018/4/18.
 */

public class ImgCompressUtils {
    /**
     * 采样率压缩
     *
     * @param path
     * @return
     */
    public static Bitmap rate(Context context, String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), R.drawable.temp, options);
        int expW = 400, expH = 400;
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        int inSampleSize;
        int sw = outWidth / expW, sh = outHeight / expH;
        if (sw > 0 && sw > sh) {
            inSampleSize = sw;
        } else if (sh > 0 && sh > sw) {
            inSampleSize = sh;
        } else {
            inSampleSize = 1;
        }
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.temp, options);
    }

    public static Bitmap quality(Context context, String path, int rate) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.temp);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, rate, baos);
        byte[] bytes = baos.toByteArray();
        return BitmapFactory.decodeByteArray(bytes, 0, baos.size());
    }
}
