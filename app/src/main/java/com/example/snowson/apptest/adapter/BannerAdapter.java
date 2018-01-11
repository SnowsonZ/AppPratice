package com.example.snowson.apptest.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.snowson.apptest.viewholder.TypeHolder;
import com.example.snowson.apptest.viewholder.ViewHolderCreator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by snowson on 17-12-27.
 */

public class BannerAdapter<T> extends PagerAdapter {
    private List<T> mDatas;
    private LinkedList<View> caches;
    private ViewHolderCreator<TypeHolder<T>> mHolderCreator;
    private ViewPager mViewPager;

    public BannerAdapter(ViewHolderCreator<TypeHolder<T>> holderCreator, List<T> datas) {
        this.mDatas = datas;
        caches = new LinkedList<View>();
        mHolderCreator = holderCreator;
    }

    public void bindViewPager(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(getRealPosition(position), container);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
        caches.add(view);
    }

    @SuppressWarnings("unchecked")
    private View getView(int position, ViewGroup container) {
        TypeHolder holder;
        View view;
        if (caches.isEmpty()) {
            holder = mHolderCreator.createHolder();
            view = holder.createView(container.getContext());
            view.setTag(holder);
        } else {
            view = caches.removeFirst();
            holder = (TypeHolder) view.getTag();
        }

        holder.bindView(container.getContext(), mDatas.get(position));
        return view;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        int position = mViewPager.getCurrentItem();
        if(position == 0) {
            position = getRealCount();
        }else if(position == getCount() - 1){
            position = getRealCount() - 1;
        }
        mViewPager.setCurrentItem(position, false);
        Log.i(getClass().getName(), "position: " + position);
    }

    private int getRealCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    private int getRealPosition(int position) {
        int realCount = getRealCount();
        if (realCount == 0) {
            return 0;
        }

        int realPosition = position % realCount;

        return realPosition;
    }
}
