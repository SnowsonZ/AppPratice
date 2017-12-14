package com.example.snowson.apptest.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.DataTypeTwo;

/**
 * Created by snowson on 17-12-14.
 */

public class TypeTwoViewHolder extends TypeAbstractViewHolder<DataTypeTwo> {

    public ImageView iv_tiny;
    public TextView tv_tiny;

    public TypeTwoViewHolder(View itemView) {
        super(itemView);
        iv_tiny = itemView.findViewById(R.id.iv_tiny);
        tv_tiny = itemView.findViewById(R.id.tv_name);
    }

    @Override
    public void bind(DataTypeTwo dataType) {
        iv_tiny.setBackgroundResource(dataType.getColorTinyPic());
        tv_tiny.setText(dataType.getTinyName());
    }
}
