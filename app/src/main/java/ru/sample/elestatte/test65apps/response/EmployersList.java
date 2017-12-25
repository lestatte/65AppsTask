package ru.sample.elestatte.test65apps.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * List of employers in response
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
public class EmployersList implements Serializable {

    @SerializedName("response")
    public List<Employer> items = null;

}
