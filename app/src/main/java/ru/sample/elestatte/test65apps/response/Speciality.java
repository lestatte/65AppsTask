package ru.sample.elestatte.test65apps.response;

        import android.arch.persistence.room.Entity;
        import android.arch.persistence.room.PrimaryKey;

        import com.google.gson.annotations.SerializedName;

/**
 * Specialty item in response
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
@SuppressWarnings("unused")
@Entity
public class Speciality {

    @PrimaryKey
    @SerializedName("specialty_id")
    public Integer id;

    @SerializedName("name")
    public String name;

}