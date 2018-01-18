package com.example.snowson.apptest.net.retrofittest;

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

}
