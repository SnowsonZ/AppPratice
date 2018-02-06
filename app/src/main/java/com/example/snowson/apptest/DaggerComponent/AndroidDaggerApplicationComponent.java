package com.example.snowson.apptest.DaggerComponent;

import com.example.snowson.apptest.DaggerModule.AndroidDaggerMainModule;
import com.example.snowson.apptest.MainApplication;

import dagger.Component;

/**
 * Created by snowson on 18-2-6.
 */
@Component(modules = AndroidDaggerMainModule.class)
public interface AndroidDaggerApplicationComponent {
    void inject(MainApplication application);
}
