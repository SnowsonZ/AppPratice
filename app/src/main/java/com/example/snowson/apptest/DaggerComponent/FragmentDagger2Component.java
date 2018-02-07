package com.example.snowson.apptest.DaggerComponent;

import com.example.snowson.apptest.fragment.Dagger2Fragment;

import dagger.Component;

/**
 * Created by snowson on 18-2-7.
 */
@Component(dependencies = ActivityDagger2Component.class)
public interface FragmentDagger2Component {
    void inject(Dagger2Fragment fragment);
}
