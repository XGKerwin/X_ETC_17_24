package com.example.x_etc_17_24;

import android.app.Application;

import org.litepal.LitePal;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/2 18:05
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
