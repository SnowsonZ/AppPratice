package com.example.snowson.apptest.DaggerModule;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by snowson on 18-2-6.
 */
@Module
public class ApplicationModule {
    Context mContext;

    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    public Context provideApplicationContext() {
        return this.mContext;
    }
}
