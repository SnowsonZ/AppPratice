package com.example.snowson.apptest.DaggerModule;

import com.example.snowson.apptest.utils.Constants;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by snowson on 18-2-7.
 */
@Module
public class ActivityDagger2Module {
    private Cache mCache;

    public ActivityDagger2Module(Cache cache) {
        this.mCache = cache;
    }

    @Provides
    public OkHttpClient.Builder provideOkHttpClient() {
        return new OkHttpClient.Builder();
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient.Builder builder) {
        return new Retrofit.Builder().baseUrl(Constants.URL.BASE_URL)
                .client(builder.build()).build();
    }
}
