package com.android.showofhands.di;

import com.android.showofhands.api.ApiModule;
import com.android.showofhands.view.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class,
        AndroidModule.class
})
public interface AppComponent {

    void inject(MainActivity activity);
}
