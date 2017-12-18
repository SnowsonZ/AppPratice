package com.example.snowson.apptest.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by snowson on 17-12-14.
 */

public abstract class TypeAbstractViewHolder<T> extends RecyclerView.ViewHolder {
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;
    public static final int TYPE_FOUR = 4;
    public static final int TYPE_FIVE = 5;
    public static final int TYPE_HEADER = 6;
    public static final int TYPE_BANNER = 7;

    public TypeAbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(T dataType);

}
