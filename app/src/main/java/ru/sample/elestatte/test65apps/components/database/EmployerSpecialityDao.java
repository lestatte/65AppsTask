package ru.sample.elestatte.test65apps.components.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ru.sample.elestatte.test65apps.response.Employer;
import ru.sample.elestatte.test65apps.response.Speciality;

/**
 * Dao for storing response from ApiClientImpl - Speciality Employer Relation
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
@Dao
public interface EmployerSpecialityDao {

    @Query("SELECT employer.* FROM employer INNER JOIN employer_speciality_join " +
           "ON employer.id = employer_speciality_join.idEmployer " +
           "WHERE employer_speciality_join.idSpeciality = :idSpeciality")
    List<Employer> getEmployerForSpeciality(final int idSpeciality);

    @Query("SELECT speciality.* FROM speciality INNER JOIN employer_speciality_join " +
            "ON speciality.id = employer_speciality_join.idSpeciality " +
            "WHERE employer_speciality_join.idEmployer = :idEmployer")
    List<Speciality> getSpecialityForEmployer(final int idEmployer);

    @Insert
    void insertAll(List<EmployerSpecialityJoin> employerSpecialityJoinList);

    @Query("DELETE FROM employer_speciality_join")
    void deleteAll();

}