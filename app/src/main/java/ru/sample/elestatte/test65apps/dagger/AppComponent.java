package ru.sample.elestatte.test65apps.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.sample.elestatte.test65apps.viewmodel.MainViewModel;

/**
 * Here we provide logic from modules (API etc)
 *
 * @author Shramko Alexey
 *         Date: 05.02.18
 */
@Singleton
@Component(modules = {APIModule.class})
public interface AppComponent {

    void inject(MainViewModel model);

    final class Initializer {
        public static AppComponent init() {
            return DaggerAppComponent.builder().build();
        }
    }
}