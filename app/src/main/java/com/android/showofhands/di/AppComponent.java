package com.android.showofhands.di;

import com.android.showofhands.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {

        AndroidModule.class
})
public interface AppComponent {

    void inject(MainActivity activity);
}
