package com.example.snowson.apptest.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.DataTypeOne;

/**
 * Created by snowson on 17-12-14.
 */

public class TypeOneViewHolder extends TypeAbstractViewHolder<DataTypeOne> {

    public ImageView iv_tiny;
    public TextView tv_tiny, tv_last_msg;

    public TypeOneViewHolder(View itemView) {
        super(itemView);
        iv_tiny = itemView.findViewById(R.id.iv_tiny);
        tv_tiny = itemView.findViewById(R.id.tv_name);
        tv_last_msg = itemView.findViewById(R.id.tv_last_msg);
    }

    @Override
    public void bind(DataTypeOne dataType) {
        iv_tiny.setBackgroundResource(dataType.getColorTinyPic());
        tv_tiny.setText(dataType.getTinyName());
        tv_last_msg.setText(dataType.getLastMsg());
    }
}
