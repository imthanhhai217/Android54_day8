package com.devpro.android54_day8;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication instances;
    public static MyApplication getInstances(){
        return instances;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
    }
}
