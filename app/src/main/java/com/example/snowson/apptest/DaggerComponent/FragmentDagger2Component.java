package com.example.snowson.apptest.DaggerComponent;

import com.example.snowson.apptest.DaggerModule.FragmentDagger2SubModule;
import com.example.snowson.apptest.fragment.Dagger2Fragment;

import dagger.Component;

/**
 * Created by snowson on 18-2-7.
 */
@com.example.snowson.apptest.inter.Dagger2Fragment
@Component(dependencies = ActivityDagger2Component.class, modules = {FragmentDagger2SubModule.class})
public interface FragmentDagger2Component {
    void inject(Dagger2Fragment fragment);
}
