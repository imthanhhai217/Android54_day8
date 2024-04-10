package com.devpro.android54_day8.interfaces;

import com.devpro.android54_day8.objects.dummy.AllProductResponse;
import com.devpro.android54_day8.objects.resful.ProductResponse;

public interface IHomeView {

    void getAllProductSuccess(AllProductResponse response);
    void getAllProductError(String error);

    void updateProductSuccess(ProductResponse response);
    void updateProductError(String error);
}
