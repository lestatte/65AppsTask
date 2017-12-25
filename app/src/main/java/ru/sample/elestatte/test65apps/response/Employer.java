package ru.sample.elestatte.test65apps.response;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Employer item in response
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
@Entity
public class Employer implements Serializable {

    @PrimaryKey
    public Integer id;

    @SerializedName("f_name")
    public String fName;

    @SerializedName("l_name")
    public String lName;

    @SerializedName("birthday")
    public String birthday;

    @SerializedName("avatr_url")
    public String avatarUrl;

    @Ignore
    @SerializedName("specialty")
    public List<Speciality> speciality = null;

}
