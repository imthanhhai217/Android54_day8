package com.devpro.android54_day8.interactors;

import com.devpro.android54_day8.api.ApiUtils;
import com.devpro.android54_day8.api.services.IApiRestfulServices;
import com.devpro.android54_day8.api.services.IDummyServices;
import com.devpro.android54_day8.interfaces.IHomePresenter;
import com.devpro.android54_day8.objects.dummy.AllProductResponse;
import com.devpro.android54_day8.objects.resful.ProductRequest;
import com.devpro.android54_day8.objects.resful.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeInteractor {

    private IHomePresenter mHomePresenter;
    private IDummyServices mDummyServices;
    private IApiRestfulServices mIApiRestfulServices;


    public HomeInteractor(IHomePresenter homePresenter) {
        this.mHomePresenter = homePresenter;
        mDummyServices = ApiUtils.getDummyServices();
        mIApiRestfulServices = ApiUtils.getRestfulServices();
    }

    public void getAllProduct() {
        mDummyServices.getAllProduct().enqueue(new Callback<AllProductResponse>() {
            @Override
            public void onResponse(Call<AllProductResponse> call, Response<AllProductResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    if (mHomePresenter != null) {
                        mHomePresenter.getAllProductSuccess(response.body());
                    }
                } else {
                    if (mHomePresenter != null) {
                        mHomePresenter.getAllProductError(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<AllProductResponse> call, Throwable throwable) {
                if (mHomePresenter != null) {
                    mHomePresenter.getAllProductError(throwable.getMessage());
                }
            }
        });
    }

    public void updateProduct(ProductRequest productRequest){
        mIApiRestfulServices.updateProduct(productRequest).enqueue(productResponseCallback);
    }

    private Callback<ProductResponse> productResponseCallback = new Callback<ProductResponse>() {
        @Override
        public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
            mHomePresenter.updateProductSuccess(response.body());
        }

        @Override
        public void onFailure(Call<ProductResponse> call, Throwable throwable) {
            mHomePresenter.updateProductError(throwable.getMessage());
        }
    };
}
