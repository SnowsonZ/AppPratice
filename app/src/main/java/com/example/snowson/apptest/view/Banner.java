package com.example.snowson.apptest.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
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
    private List<String> mImgUrl = new ArrayList<String>();
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
        View rootView = LayoutInflater.from(context).inflate(R.layout.banner, this, true);
        setBackgroundColor(Color.TRANSPARENT);
        vpContent = rootView.findViewById(R.id.main_banner);
        ctrIndicator = rootView.findViewById(R.id.ctr_indicator);
        vpContent.setPageMargin((int) TypedValue.applyDimension(TypedValue.TYPE_DIMENSION,
                2, getResources().getDisplayMetrics()));
//        addView(rootView);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initData();
    }

    public void setData(List<String> imgUrl) {
        if (imgUrl != null && imgUrl.size() > 0) {
            mImgUrl.addAll(imgUrl);
        }
    }

    public void initData() {
        for (int i = 0; i < mImgUrl.size(); i++) {
            CardView item = new CardView(getContext());
            item.setCardBackgroundColor(Color.parseColor(mImgUrl.get(i)));
            item.setRadius(20);
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
        adapter.notifyDataSetChanged();
        vpContent.setCurrentItem(1);
    }

    private void initIndicator() {
        for (int i = 0; i < mImgUrl.size(); i++) {
            ImageView item = new ImageView(getContext());
            item.setBackgroundResource(R.drawable.indicator_point_normal);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) item.getLayoutParams();
            if (params == null) {
                params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT
                        , LinearLayout.LayoutParams.WRAP_CONTENT);
            }
            params.setMargins(0, 0,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
                            getResources().getDisplayMetrics()), 0);
            item.setLayoutParams(params);
            ctrIndicator.addView(item);
        }
        ctrIndicator.getChildAt(0).setBackgroundResource(R.drawable.indicator_point_selected);
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
