package com.devpro.android54_day8.api;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.devpro.android54_day8.MyApplication;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "https://dummyjson.com/";
    private static Retrofit instances;

    public static Retrofit getInstances() {
        if (instances == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new ChuckerInterceptor(MyApplication.getInstances().getApplicationContext()))
                    .addInterceptor(loggingInterceptor)
                    .build();

            instances = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instances;
    }
}
