package ru.sample.elestatte.test65apps.dagger;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ru.sample.elestatte.test65apps.components.EmployerRepositoryImpl;
import ru.sample.elestatte.test65apps.components.IEmployerRepository;

/**
 * Module for work with local storage
 *
 * @author Shramko Alexey
 *         Date: 05.02.18
 */
@Module
interface DBModule {

    /**
     * @param impl current DB agent implementation
     * @return interface for interaction with DB agent implementation
     */
    @Binds
    @Singleton
    IEmployerRepository providesApiClient(EmployerRepositoryImpl impl);
}