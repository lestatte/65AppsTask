package ru.sample.elestatte.test65apps.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Employer item in response
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
@SuppressWarnings("unused")
public class Employer {

    @SerializedName("f_name")
    public String fName;

    @SerializedName("l_name")
    public String lName;

    @SerializedName("birthday")
    public String birthday;

    @SerializedName("avatr_url")
    public String avatarUrl;

    @SerializedName("specialty")
    public List<Speciality> speciality = null;

}
