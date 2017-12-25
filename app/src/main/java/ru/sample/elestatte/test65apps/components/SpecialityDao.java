package ru.sample.elestatte.test65apps.components;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.sample.elestatte.test65apps.response.Speciality;

/**
 * Dao for storing response from ApiClient - Speciality item
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
@Dao
public interface SpecialityDao {

    @Query("SELECT * FROM speciality")
    List<Speciality> getAll();

    @Insert
    void insertAll(List<Speciality> specialities);

    @Query("DELETE FROM speciality")
    void deleteAll();

}