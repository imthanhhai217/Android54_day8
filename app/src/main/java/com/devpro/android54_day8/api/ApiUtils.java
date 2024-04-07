package com.devpro.android54_day8.api;

import com.devpro.android54_day8.api.services.IDummyServices;

public class ApiUtils {

    public static IDummyServices getDummyServices(){
        return RetrofitClient.getInstances().create(IDummyServices.class);
    }
}
