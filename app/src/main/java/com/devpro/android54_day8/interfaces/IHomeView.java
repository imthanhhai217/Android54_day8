package com.devpro.android54_day8.interfaces;

import com.devpro.android54_day8.objects.AllProductResponse;

public interface IHomeView {

    void getAllProductSuccess(AllProductResponse response);
    void getAllProductError(String error);
}
