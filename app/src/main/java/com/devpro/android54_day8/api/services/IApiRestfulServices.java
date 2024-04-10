package com.devpro.android54_day8.api.services;

import com.devpro.android54_day8.objects.resful.ProductRequest;
import com.devpro.android54_day8.objects.resful.ProductResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IApiRestfulServices {

    @POST("objects")
    Call<ProductResponse> updateProduct(@Body ProductRequest request);
}
