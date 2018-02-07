package com.example.snowson.apptest.DaggerModule;

import com.example.snowson.apptest.utils.Constants;

import dagger.Module;
import dagger.Provides;
import okhttp3.Request;

/**
 * Created by snowson on 18-2-7.
 */
@Module
public class FragmentDagger2SubModule {

    @Provides
    public Request provideRequest() {
        return new Request.Builder().url(Constants.URL.BASE_URL).build();
    }
}
