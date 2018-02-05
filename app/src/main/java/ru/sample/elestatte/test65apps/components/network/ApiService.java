package ru.sample.elestatte.test65apps.components.network;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import ru.sample.elestatte.test65apps.response.EmployersList;

/**
 * Here is described all API interfaces for data loading from server
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
public interface ApiService {

    @GET("testTask.json")
    Observable<EmployersList> fetchEmployers();

    final class Initializer {
        public static Retrofit init() {
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

}
