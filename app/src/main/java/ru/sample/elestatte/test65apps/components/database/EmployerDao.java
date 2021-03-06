package ru.sample.elestatte.test65apps.components.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.sample.elestatte.test65apps.response.Employer;

/**
 * Dao for storing response from ApiClientImpl - Employer item
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
@Dao
public interface EmployerDao {

    @Query("SELECT * FROM employer")
    List<Employer> getAll();

    @Insert
    void insertAll(List<Employer> employers);

    @Query("DELETE FROM employer")
    void deleteAll();

}
