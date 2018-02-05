package ru.sample.elestatte.test65apps.dagger;

import javax.inject.Singleton;

import dagger.Component;
import ru.sample.elestatte.test65apps.App65;
import ru.sample.elestatte.test65apps.viewmodel.FilteredEmployerListViewModel;
import ru.sample.elestatte.test65apps.viewmodel.MainViewModel;

/**
 * Here we provide logic from modules (API etc)
 *
 * @author Shramko Alexey
 *         Date: 05.02.18
 */
@Singleton
@Component(modules = {APIModule.class, DBModule.class, AppModule.class})
public interface AppComponent {

    void inject(MainViewModel model);

    void inject(FilteredEmployerListViewModel model);

    final class Initializer {
        public static AppComponent init(App65 app) {
            return DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
    }
}