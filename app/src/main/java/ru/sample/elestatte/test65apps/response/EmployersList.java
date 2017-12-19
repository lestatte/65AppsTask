package ru.sample.elestatte.test65apps.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * List of employers in response
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
@SuppressWarnings("unused")
public class EmployersList {

    @SerializedName("response")
    private List<Employer> items = null;

}
