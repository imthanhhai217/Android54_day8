package com.devpro.android54_day8.api.services;

import com.devpro.android54_day8.objects.dummy.AllProductResponse;
import com.devpro.android54_day8.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IDummyServices {
    @GET(Constant.GET_ALL_PRODUCT_API)
    Call<AllProductResponse> getAllProduct();
}
