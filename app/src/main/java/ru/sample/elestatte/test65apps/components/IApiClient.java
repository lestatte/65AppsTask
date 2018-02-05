package ru.sample.elestatte.test65apps.components;

import io.reactivex.Observable;
import ru.sample.elestatte.test65apps.response.EmployersList;

/**
 * Common interface for receiving data from API
 *
 * @author Shramko Alexey
 *         Date: 05.02.18
 */
public interface IApiClient {

    Observable<EmployersList> fetchEmployers();

}
