package com.ramilforflatstack;


import com.activeandroid.ActiveAndroid;

public class AppDelegate extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}



