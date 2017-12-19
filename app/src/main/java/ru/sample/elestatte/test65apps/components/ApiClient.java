package ru.sample.elestatte.test65apps.components;

import io.reactivex.Observable;
import ru.sample.elestatte.test65apps.response.EmployersList;

/**
 * API implementation
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
public class ApiClient {

    static Observable<EmployersList> fetchEmployers() {
        return Observable.empty();
    }

}
