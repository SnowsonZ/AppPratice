package com.example.snowson.apptest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.snowson.apptest.adapter.IndexContentAdapter;
import com.example.snowson.apptest.fragment.BaseFragment;
import com.example.snowson.apptest.fragment.PageDialogFragment;
import com.example.snowson.apptest.fragment.PageIndexFragment;
import com.example.snowson.apptest.fragment.PageViewTypeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mNavTab;
    private ViewPager mContentVp;
    private List<BaseFragment> mPages;
    private IndexContentAdapter mAdapter;
    private List<String> mPageTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mNavTab = findViewById(R.id.tabl_index);
        mContentVp = findViewById(R.id.vp_index);
    }

    private void initData() {
        mPageTitle = new ArrayList<String>();
        mPageTitle.add("Home");
        mPageTitle.add("Dialog");
        mPageTitle.add("ViewType");
        mPages = new ArrayList<BaseFragment>();
        PageIndexFragment fragIndex = new PageIndexFragment();
        Bundle bIndex = new Bundle();
        bIndex.putString("mPageTitle", "首页");
        fragIndex.setArguments(bIndex);
        mPages.add(fragIndex);
        PageDialogFragment fragDialog = new PageDialogFragment();
        Bundle bDialog = new Bundle();
        bDialog.putString("mPageTitle", "Dialog");
        fragDialog.setArguments(bDialog);
        mPages.add(fragDialog);
        PageViewTypeFragment fragViewType = new PageViewTypeFragment();
        Bundle bViewType = new Bundle();
        bViewType.putString("mPageTitle", "ViewType");
        fragViewType.setArguments(bViewType);
        mPages.add(fragViewType);
        mAdapter = new IndexContentAdapter(getSupportFragmentManager(), mPages, mPageTitle);
        mContentVp.setAdapter(mAdapter);
        mNavTab.setupWithViewPager(mContentVp);
    }
}
