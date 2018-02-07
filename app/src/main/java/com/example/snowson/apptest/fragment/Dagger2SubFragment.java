package com.example.snowson.apptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.snowson.apptest.DaggerModule.FragmentDagger2SubModule;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.activity.Dagger2Activity;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import okhttp3.Request;
import retrofit2.Retrofit;

/**
 * Created by snowson on 18-2-7.
 */

public class Dagger2SubFragment extends Fragment {

    @Inject
    Retrofit mRetrofit;
    @Inject
    Request mRequest;

    public static Dagger2SubFragment getInstance(Bundle bundle) {
        Dagger2SubFragment fragment = new Dagger2SubFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dagger2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        ((Dagger2Activity) getActivity()).getComponent()
//                .fragmentDagger2SubComponent().inject(this);
        ((Dagger2Activity) getActivity()).getComponent().fragmentBuilder()
                .FragmentDaggerSubModule(new FragmentDagger2SubModule()).build()
                .inject(this);
        Logger.d("Fragment with subComponent --> baseUrl:" + mRetrofit.baseUrl());
        Logger.d("Fragment with subComponent --> Request: " + mRequest);
    }
}
