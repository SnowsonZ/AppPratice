package com.example.snowson.apptest.DaggerComponent;

import com.example.snowson.apptest.DaggerModule.MainModule;
import com.example.snowson.apptest.bean.dagger2.CartBean;

import dagger.Component;

/**
 * Created by snowson on 18-2-6.
 */
@Component(modules = MainModule.class)
public interface BuilderTestComponent {

    CartBean getCartBean();

    @Component.Builder
    interface Builder {
        BuilderTestComponent build();

        Builder mainModule(MainModule mainModule);
    }
}
