package com.devpro.android54_day8.presenters;

import com.devpro.android54_day8.interactors.HomeInteractor;
import com.devpro.android54_day8.interfaces.IHomePresenter;
import com.devpro.android54_day8.interfaces.IHomeView;
import com.devpro.android54_day8.objects.AllProductResponse;

public class HomePresenter implements IHomePresenter {

    private IHomeView mHomeView;
    private HomeInteractor mHomeInteractor;

    public HomePresenter(IHomeView iHomeView){
        this.mHomeView = iHomeView;
        mHomeInteractor = new HomeInteractor(this);
    }

    public void getAllProduct(){
        mHomeInteractor.getAllProduct();
    }


    @Override
    public void getAllProductSuccess(AllProductResponse response) {
        if (mHomeView != null){
            mHomeView.getAllProductSuccess(response);
        }
    }

    @Override
    public void getAllProductError(String error) {
        if (mHomeView != null){
            mHomeView.getAllProductError(error);
        }
    }
}
