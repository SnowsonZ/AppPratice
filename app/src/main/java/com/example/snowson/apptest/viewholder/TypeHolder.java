package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.view.View;

/**
 * Created by snowson on 17-12-27.
 */

public interface TypeHolder<T> {
    View createView(Context contex);

    void bindView(Context context, T resId);
}
