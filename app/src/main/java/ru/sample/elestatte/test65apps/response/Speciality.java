package ru.sample.elestatte.test65apps.response;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Specialty item in response
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
@Entity
public class Speciality implements Serializable {

    @PrimaryKey
    @SerializedName("specialty_id")
    public Integer id;

    @SerializedName("name")
    public String name;

    public Speciality(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

}