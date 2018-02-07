package com.example.snowson.apptest.DaggerComponent;

import com.example.snowson.apptest.DaggerModule.FragmentDagger2SubModule;
import com.example.snowson.apptest.fragment.Dagger2SubFragment;
import com.example.snowson.apptest.inter.Dagger2Fragment;

import dagger.Subcomponent;

/**
 * Created by snowson on 18-2-7.
 */
@Dagger2Fragment
@Subcomponent(modules = {FragmentDagger2SubModule.class})
public interface FragmentDagger2SubComponent {
    void inject(Dagger2SubFragment fragment);

    /**
     * 当SubComponent需要自己的依赖注入时
     */
    @Subcomponent.Builder
    interface Builder {
        FragmentDagger2SubComponent build();

        Builder FragmentDaggerSubModule(FragmentDagger2SubModule module);
    }

}
