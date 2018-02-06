package com.example.snowson.apptest.DaggerComponent;

import com.example.snowson.apptest.activity.AndroidDaggerActivity;
import com.example.snowson.apptest.bean.dagger2.CartBean;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by snowson on 18-2-6.
 */
@Subcomponent(modules = {AndroidInjectionModule.class, AndroidDaggerSubComponent.SubModule.class})
public interface AndroidDaggerSubComponent extends AndroidInjector<AndroidDaggerActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<AndroidDaggerActivity> {

    }

    @Module
    class SubModule {
        @Provides
        public CartBean provideCartBean() {
            return new CartBean("dagger android create", 10, 12.32f);
        }

        @Provides
        public String providePckName() {
            return AndroidDaggerActivity.class.getName();
        }
    }
}
