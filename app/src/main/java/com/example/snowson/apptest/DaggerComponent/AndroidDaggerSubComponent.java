package com.example.snowson.apptest.DaggerComponent;

import com.example.snowson.apptest.activity.AndroidDaggerActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by snowson on 18-2-6.
 */
@Subcomponent(modules = AndroidInjectionModule.class)
public interface AndroidDaggerSubComponent extends AndroidInjector<AndroidDaggerActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<AndroidDaggerActivity> {

    }
}
