package ru.sample.elestatte.test65apps.components;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.sample.elestatte.test65apps.response.Employer;

/**
 * Dao for storing response from ApiClient - Speciality Employer Relation
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
@SuppressWarnings("unused")
@Dao
public interface EmployerSpecialityDao {

    @Query("SELECT employer.* FROM employer INNER JOIN employer_speciality_join " +
           "ON employer.id = employer_speciality_join.idEmployer " +
           "WHERE employer_speciality_join.idSpeciality = :idSpeciality")
    List<Employer> getEmployerForSpeciality(final int idSpeciality);

    @Insert
    void insertAll(List<EmployerSpecialityJoin> employerSpecialityJoinList);

    @Query("DELETE FROM employer_speciality_join")
    void deleteAll();

}