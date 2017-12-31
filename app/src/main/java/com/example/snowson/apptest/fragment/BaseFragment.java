package com.example.snowson.apptest.fragment;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

/**
 * author: snowson
 * created on: 17-12-31 下午9:59
 * description:
 */

public abstract class BaseFragment extends Fragment {

    protected String mTitle;

    public String getPageTitle() {
        return TextUtils.isEmpty(mTitle) ? "" : mTitle;
    };
}
