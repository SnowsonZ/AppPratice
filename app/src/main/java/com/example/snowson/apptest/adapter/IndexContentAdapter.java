package com.example.snowson.apptest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.snowson.apptest.fragment.BaseFragment;

import java.util.List;

/**
 * author: snowson
 * created on: 17-12-31 下午9:05
 * description:
 */

public class IndexContentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mPages;
    private List<String> mPageTitle;
    public IndexContentAdapter(FragmentManager fm, List<BaseFragment> pages, List<String> pageTitle) {
        super(fm);
        mPages = pages;
        mPageTitle = pageTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return mPages.get(position);
    }

    @Override
    public int getCount() {
        return mPages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitle.get(position);
    }
}
