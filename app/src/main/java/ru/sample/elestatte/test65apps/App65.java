package ru.sample.elestatte.test65apps;

import android.app.Application;

import ru.sample.elestatte.test65apps.dagger.AppComponent;

/**
 * Application overloaded for dagger routine
 *
 * @author Shramko Alexey
 *         Date: 05.02.18
 */
public class App65 extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sAppComponent = buildComponentGraph();
    }

    private AppComponent buildComponentGraph() {
        return AppComponent.Initializer.init(this);
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
