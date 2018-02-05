package ru.sample.elestatte.test65apps.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.sample.elestatte.test65apps.App65;

/**
 * Module for providing application context and etc
 *
 * @author Shramko Alexey
 *         Date: 06.02.18
 */
@Module
class AppModule {

    private final App65 mApp;

    AppModule(App65 application) {
        mApp = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApp;
    }

}
