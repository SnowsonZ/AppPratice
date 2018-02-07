package com.example.snowson.apptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.snowson.apptest.DaggerComponent.ActivityDagger2Component;
import com.example.snowson.apptest.DaggerComponent.DaggerFragmentDagger2Component;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.activity.Dagger2Activity;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import okhttp3.Request;
import retrofit2.Retrofit;

/**
 * Created by snowson on 18-2-7.
 */
public class Dagger2Fragment extends Fragment {

    @Inject
    Retrofit mRetrofit;
    @Inject
    Request mRequest;
    @Inject
    Request mRequest1;

    public static Dagger2Fragment getInstance(Bundle bundle) {
        Dagger2Fragment fragment = new Dagger2Fragment();
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
        ActivityDagger2Component component = ((Dagger2Activity) getActivity()).getComponent();
        DaggerFragmentDagger2Component.builder()
                .activityDagger2Component(component).build().inject(this);
        Logger.d("Fragment with depdency: " + mRetrofit.baseUrl());
        Logger.d("Fragment with depdency: " + mRequest.hashCode());
        Logger.d("Fragment with depdency: " + mRequest1.hashCode());
    }
}
