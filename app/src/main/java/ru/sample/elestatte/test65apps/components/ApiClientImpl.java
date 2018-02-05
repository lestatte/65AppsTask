package ru.sample.elestatte.test65apps.components;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import ru.sample.elestatte.test65apps.components.network.ApiService;
import ru.sample.elestatte.test65apps.response.EmployersList;

/**
 * API implementation (Retrofit)
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
@Singleton
public class ApiClientImpl implements IApiClient {

    @NonNull
    private ApiService mApiService;

    @Inject
    ApiClientImpl() {
        mApiService = ApiService.Initializer.init().create(ApiService.class);
    }

    @Override
    public Observable<EmployersList> fetchEmployers() {
        return mApiService.fetchEmployers();
    }
}
