package ru.sample.elestatte.test65apps.response;

import com.google.gson.annotations.SerializedName;

/**
 * Specialty item in response
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
@SuppressWarnings("unused")
public class Speciality {

    @SerializedName("specialty_id")
    public Integer id;

    @SerializedName("name")
    public String name;

}