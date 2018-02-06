package com.example.snowson.apptest.DaggerModule;

import android.app.Activity;

import com.example.snowson.apptest.DaggerComponent.AndroidDaggerSubComponent;
import com.example.snowson.apptest.activity.AndroidDaggerActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by snowson on 18-2-6.
 */
@Module(subcomponents = AndroidDaggerSubComponent.class)
public abstract class AndroidDaggerMainModule {
    @Binds
    @IntoMap
    @ActivityKey(AndroidDaggerActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    bindAndroidDaggerActivityInjector(AndroidDaggerSubComponent.Builder builder);
}
