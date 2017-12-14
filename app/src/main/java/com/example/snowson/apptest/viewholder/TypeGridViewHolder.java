package com.example.snowson.apptest.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.DataTypeGrid;

/**
 * Created by snowson on 17-12-14.
 */

public class TypeGridViewHolder extends TypeAbstractViewHolder<DataTypeGrid> {

    public ImageView iv_goods;
    public TextView tv_goods_name;

    public TypeGridViewHolder(View itemView) {
        super(itemView);
        iv_goods = itemView.findViewById(R.id.iv_goods);
        tv_goods_name = itemView.findViewById(R.id.tv_goos_name);
    }

    @Override
    public void bind(DataTypeGrid dataType) {
        iv_goods.setBackgroundResource(dataType.getGoodsPic());
        tv_goods_name.setText(dataType.getGoodsName());
    }
}
