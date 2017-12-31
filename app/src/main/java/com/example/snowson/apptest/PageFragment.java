package com.example.snowson.apptest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: snowson
 * created on: 17-12-31 下午9:20
 * description:
 */

public class PageFragment extends Fragment {

    private int mLayoutId;

    public static PageFragment newInstance(int resId) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("layoutId", resId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle params = getArguments();
        if(params != null) {
            mLayoutId = params.getInt("layoutId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(mLayoutId, container, false);
        return rootView;
    }
}
