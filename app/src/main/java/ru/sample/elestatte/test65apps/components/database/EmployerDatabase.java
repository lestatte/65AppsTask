package ru.sample.elestatte.test65apps.components.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.sample.elestatte.test65apps.response.Employer;
import ru.sample.elestatte.test65apps.response.Speciality;

/**
 * Google Room database implementation
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
@Database(entities = { Employer.class, Speciality.class, EmployerSpecialityJoin.class },
          version = 1)
public abstract class EmployerDatabase extends RoomDatabase {

    public abstract EmployerDao getEmployerDao();
    public abstract SpecialityDao getSpecialityDao();
    public abstract EmployerSpecialityDao getEmployerSpecialityDao();

}