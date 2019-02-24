package com.android.showofhands;

import android.app.Application;

import com.android.showofhands.di.AndroidModule;
import com.android.showofhands.di.AppComponent;
import com.android.showofhands.di.DaggerAppComponent;

public class ShowOfHandsApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();

    }

    public AppComponent appComponent(){return appComponent;}
}
