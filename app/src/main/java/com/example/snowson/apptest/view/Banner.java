package com.example.snowson.apptest.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.snowson.apptest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snowson on 17-12-15.
 */

public class Banner extends RelativeLayout {
    private List<Integer> mImgUrl = new ArrayList<Integer>();
    private List<CardView> mContent = new ArrayList<CardView>();
    private ViewPager vpContent;
    private LinearLayout ctrIndicator;
    private List<ImageView> mIndicator = new ArrayList<ImageView>();

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
        View rootView = LayoutInflater.from(context).inflate(R.layout.banner, this, false);
        vpContent = rootView.findViewById(R.id.main_banner);
        ctrIndicator = rootView.findViewById(R.id.ctr_indicator);

    }

    public void initData(List<Integer> imgUrl) {
        if (imgUrl != null && imgUrl.size() > 0) {
            mImgUrl.addAll(imgUrl);
        }
        for (int i = 0; i < mImgUrl.size(); i++) {
            CardView item = new CardView(getContext());
            item.setBackgroundResource(mImgUrl.get(i));
            mContent.add(item);
        }
        initIndicator();
        BannerAdapter adapter = new BannerAdapter();
        vpContent.setAdapter(adapter);
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ctrIndicator.getChildCount(); i++) {
                    if (i == position) {
                        ctrIndicator.getChildAt(i).
                                setBackgroundResource(R.drawable.indicator_point_selected);
                    } else {
                        ctrIndicator.getChildAt(i).
                                setBackgroundResource(R.drawable.indicator_point_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIndicator() {
        for (int i = 0; i < mImgUrl.size(); i++) {
            ImageView item = new ImageView(getContext());
            item.setBackgroundResource(R.drawable.indicator_point_normal);
            ctrIndicator.addView(item);
        }
    }

    class BannerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mContent.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            CardView item = mContent.get(position);
            container.addView(item);
            return item;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
