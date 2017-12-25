package ru.sample.elestatte.test65apps.components;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.sample.elestatte.test65apps.response.EmployersList;

/**
 * API implementation
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
public class ApiClient {

    public static Observable<EmployersList> fetchEmployers() {
        return createRetrofit().create(ApiService.class).fetchEmployers();
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://gitlab.65apps.com/65gb/static/raw/master/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create(
                                new GsonBuilder().setLenient().create()))
                .client(new OkHttpClient.Builder().build())
                .build();
    }
}
