package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.snowson.apptest.DaggerComponent.ActivityDagger2Component;
import com.example.snowson.apptest.DaggerComponent.DaggerActivityDagger2Component;
import com.example.snowson.apptest.DaggerModule.ActivityDagger2Module;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.dagger2.CartBean;
import com.example.snowson.apptest.fragment.Dagger2Fragment;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class Dagger2Activity extends AppCompatActivity {

    @Inject
    CartBean mCBean;
    @Inject
    OkHttpClient.Builder mBuilder;
    @Inject
    Retrofit mRetrofit;
    FrameLayout mFragmentFLayout;
    private ActivityDagger2Component mComponent;

    private static final int MAX_CACHE = 100 * 1024 * 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        mComponent = DaggerActivityDagger2Component.builder()
                .activityDagger2Module(new ActivityDagger2Module(new Cache(getCacheDir(), MAX_CACHE)))
                .build();
        mComponent.inject(this);
        Logger.d(mCBean.getgBean().hashCode());
        Logger.d(mCBean.getgBean().hashCode());
        Logger.d(mBuilder.hashCode());
        Logger.d(mRetrofit.hashCode());
        getSupportFragmentManager().beginTransaction().add(R.id.flayout_fragment,
                Dagger2Fragment.getInstance(null), Dagger2Fragment.class.getName()).commit();
    }

    public ActivityDagger2Component getComponent() {
        return mComponent;
    }
}
