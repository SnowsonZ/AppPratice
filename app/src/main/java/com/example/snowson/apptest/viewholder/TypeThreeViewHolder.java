package com.example.snowson.apptest.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.DataTypeThree;

/**
 * Created by snowson on 17-12-14.
 */

public class TypeThreeViewHolder extends TypeAbstractViewHolder<DataTypeThree> {

    public ImageView iv_tiny, iv_bg;
    public TextView tv_tiny;

    public TypeThreeViewHolder(View itemView) {
        super(itemView);
        iv_tiny = itemView.findViewById(R.id.iv_tiny);
        tv_tiny = itemView.findViewById(R.id.tv_name);
        iv_bg = itemView.findViewById(R.id.iv_bg);
    }

    @Override
    public void bind(DataTypeThree dataType) {
        iv_tiny.setBackgroundResource(dataType.getColorTinyPic());
        tv_tiny.setText(dataType.getTinyName());
        iv_bg.setBackgroundResource(dataType.getColorBg());
    }
}
