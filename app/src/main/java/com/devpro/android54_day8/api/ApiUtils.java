package com.devpro.android54_day8.api;

import com.devpro.android54_day8.api.services.IApiRestfulServices;
import com.devpro.android54_day8.api.services.IDummyServices;

public class ApiUtils {

    public static IDummyServices getDummyServices(){
        return RetrofitClient.getInstances().create(IDummyServices.class);
    }

    public static IApiRestfulServices getRestfulServices(){
        return DemoRetrofitClient.getInstances().create(IApiRestfulServices.class);
    }
}
