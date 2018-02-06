package com.example.snowson.apptest.DaggerComponent;

import com.example.snowson.apptest.DaggerModule.MainModule;
import com.example.snowson.apptest.activity.Dagger2Activity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by snowson on 18-2-5.
 */
@Component(dependencies = ApplicationComponent.class, modules = MainModule.class)
@Singleton
public interface MainComponent {
    void inject(Dagger2Activity activity);
}
