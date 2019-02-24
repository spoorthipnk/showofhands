package com.android.showofhands.di;

import android.app.Application;
import android.content.Context;

import com.android.showofhands.view.ui.App;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {

    private Application application;

    public AndroidModule(Application application) {
        this.application = application;
    }

    @Provides
    @App
    Context provideAppContent(){
        return application;
    }


}
