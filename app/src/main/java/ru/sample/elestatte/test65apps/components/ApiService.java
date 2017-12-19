package ru.sample.elestatte.test65apps.components;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ru.sample.elestatte.test65apps.response.EmployersList;

/**
 * Here is described all API interfaces for data loading from server
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
interface ApiService {
    @GET("http://gitlab.65apps.com/65gb/static/blob/master/testTask.json")
    Observable<EmployersList> fetchEmployers();
}
