package com.example.snowson.apptest.DaggerComponent;

import android.content.Context;

import com.example.snowson.apptest.DaggerModule.ApplicationModule;

import dagger.Component;

/**
 * Created by snowson on 18-2-6.
 */
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Context getContext();
}
