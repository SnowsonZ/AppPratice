package com.example.snowson.apptest.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.utils.TypeData;

/**
 * Created by snowson on 18-1-10.
 */

@Deprecated
public class CartFooterViewHolder extends BaseHeaderViewHolder {

    @Override
    public View createView(LayoutInflater inflater, ViewGroup parent, boolean attachToRoot) {
        View convertView = inflater.inflate(R.layout.item_cart_footer,
                parent, false);
        return convertView;
    }

    @Override
    public void bindView(Context context, TypeData bean) {
        super.bindView(context, bean);
    }
}
