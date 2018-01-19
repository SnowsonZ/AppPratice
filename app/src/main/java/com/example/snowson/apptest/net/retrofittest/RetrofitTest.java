package com.example.snowson.apptest.net.retrofittest;

import com.example.snowson.apptest.bean.rx.BaseUserInfo;
import com.example.snowson.apptest.bean.rx.ExtraUserInfo;
import com.example.snowson.apptest.bean.rx.LoginRequest;
import com.example.snowson.apptest.bean.rx.LoginResponse;
import com.example.snowson.apptest.bean.rx.RegisterRequest;
import com.example.snowson.apptest.bean.rx.RegisterResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by snowson on 18-1-18.
 */

public interface RetrofitTest {

    @GET
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET
    Observable<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @GET
    Observable<BaseUserInfo> getBaseUserinfo();

    @GET
    Observable<ExtraUserInfo> getExtraUserInfo();

}
