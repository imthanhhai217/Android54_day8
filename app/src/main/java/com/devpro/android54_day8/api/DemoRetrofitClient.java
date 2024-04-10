package com.devpro.android54_day8.api;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.devpro.android54_day8.MyApplication;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DemoRetrofitClient {

    private static final String BASE_URL = "https://api.restful-api.dev/";
    private static Retrofit instances;

    public static Retrofit getInstances(){
        if (instances == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new ChuckerInterceptor(MyApplication.getInstances().getApplicationContext()))
                    .build();

            instances = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  instances;
    }

}
