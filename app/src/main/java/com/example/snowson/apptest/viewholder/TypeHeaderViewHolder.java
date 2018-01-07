package com.example.snowson.apptest.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.snowson.apptest.R;

/**
 * Created by snowson on 17-12-14.
 */

public class TypeHeaderViewHolder extends TypeAbstractViewHolder<String> {

    private TextView tv_name;

    public TypeHeaderViewHolder(View itemView) {
        super(itemView);
        tv_name = itemView.findViewById(R.id.tv_header);
    }

    @Override
    public void bind(String typeName) {
        tv_name.setText(typeName);
    }
}
