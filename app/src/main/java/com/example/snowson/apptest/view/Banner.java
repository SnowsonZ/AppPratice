package com.example.snowson.apptest.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.adapter.BannerAdapter;
import com.example.snowson.apptest.transition.NoticePageTransformer;
import com.example.snowson.apptest.utils.ScreenUtils;
import com.example.snowson.apptest.viewholder.BannerHolderView;
import com.example.snowson.apptest.viewholder.TypeHolder;
import com.example.snowson.apptest.viewholder.ViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snowson on 17-12-15.
 */

public class Banner extends RelativeLayout {
    private List<String> mImgUrl = new ArrayList<String>();
    private ViewPager vpContent;
    private LinearLayout ctrIndicator;

    public Banner(Context context) {
        super(context);
        initView(context);
    }

    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Banner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Banner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.banner, this, true);
        setBackgroundColor(Color.TRANSPARENT);
        vpContent = rootView.findViewById(R.id.main_banner);
        ctrIndicator = rootView.findViewById(R.id.ctr_indicator);
        vpContent.setPageMargin((int) (ScreenUtils.dp2px(getContext(), 14)));
        vpContent.setPageTransformer(true, new NoticePageTransformer());
    }

    @SuppressWarnings("unchecked")
    public void initData(List<String> imgUrl) {
        if (imgUrl == null && imgUrl.size() <= 0) {
            return;
        }
        mImgUrl.addAll(imgUrl);
        initIndicator();
        BannerAdapter adapter = new BannerAdapter(new ViewHolderCreator<TypeHolder>() {
            @Override
            public TypeHolder createHolder() {
                return new BannerHolderView();
            }
        }, mImgUrl);
        vpContent.setAdapter(adapter);
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int realPosition = position % mImgUrl.size();
                for (int i = 0; i < ctrIndicator.getChildCount(); i++) {
                    if (i == realPosition) {
                        ctrIndicator.getChildAt(i % mImgUrl.size()).
                                setBackgroundResource(R.drawable.indicator_point_selected);
                    } else {
                        ctrIndicator.getChildAt(i % mImgUrl.size()).
                                setBackgroundResource(R.drawable.indicator_point_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adapter.notifyDataSetChanged();
        vpContent.setCurrentItem(mImgUrl.size() / 2);
    }

    private void initIndicator() {
        ctrIndicator.removeAllViews();
        for (int i = 0; i < mImgUrl.size(); i++) {
            ImageView item = new ImageView(getContext());
            item.setBackgroundResource(R.drawable.indicator_point_normal);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) item.getLayoutParams();
            if (params == null) {
                params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                        , LinearLayout.LayoutParams.WRAP_CONTENT);
            }
            if (i < mImgUrl.size() - 1) {
                params.setMargins(0, 0,
                        (int) (ScreenUtils.dp2px(getContext(), 10)), 0);
                item.setLayoutParams(params);
            }
            ctrIndicator.addView(item);
        }
        ctrIndicator.getChildAt(0).setBackgroundResource(R.drawable.indicator_point_selected);
    }
}
