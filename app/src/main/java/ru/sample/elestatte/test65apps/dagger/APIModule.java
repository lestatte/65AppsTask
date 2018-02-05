package ru.sample.elestatte.test65apps.dagger;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ru.sample.elestatte.test65apps.components.ApiClientImpl;
import ru.sample.elestatte.test65apps.components.IApiClient;

/**
 * Module for REST API
 *
 * @author Shramko Alexey
 *         Date: 05.02.18
 */
@Module
interface APIModule {

    /**
     * We can quickly rework this place. For example, we can replace
     * current implementation with fabric combined with strategy.
     *
     * @param impl current api implementation
     * @return interface for interaction with current api implementation
     */
    @Binds
    @Singleton
    IApiClient providesApiClient(ApiClientImpl impl);
}
