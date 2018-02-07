package com.example.snowson.apptest.DaggerComponent;

import com.example.snowson.apptest.DaggerModule.ActivityDagger2Module;
import com.example.snowson.apptest.activity.Dagger2Activity;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by snowson on 18-2-7.
 */

/**
 * Component与SubComponent的scope必须不同
 */
@com.example.snowson.apptest.inter.Dagger2Activity
@Component(modules = {ActivityDagger2Module.class})
public interface ActivityDagger2Component {
    void inject(Dagger2Activity activity);
    Retrofit newRetrofit();
//    FragmentDagger2SubComponent fragmentDagger2SubComponent();
    FragmentDagger2SubComponent.Builder fragmentBuilder();
}
