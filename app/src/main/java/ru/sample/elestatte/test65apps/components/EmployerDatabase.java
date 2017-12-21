package ru.sample.elestatte.test65apps.components;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.SparseArray;

import java.util.List;

import ru.sample.elestatte.test65apps.response.Employer;
import ru.sample.elestatte.test65apps.response.Speciality;
import ru.sample.elestatte.test65apps.utility.Utils;

/**
 * Google Room database implementation
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
@SuppressWarnings("unused")
@Database(entities = { Employer.class, Speciality.class }, version = 1)
public abstract class EmployerDatabase extends RoomDatabase {

    private static EmployerDatabase INSTANCE = null;

    public abstract EmployerDao getEmployerDao();
    public abstract SpecialityDao getSpecialityDao();

    public static EmployerDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    EmployerDatabase.class, "employers-database").build();
        }
        return INSTANCE;
    }

    public void putData(List<Employer> items) {
        EmployerDao employerDao = getEmployerDao();
        employerDao.deleteAll();
        SpecialityDao specialityDao = getSpecialityDao();
        specialityDao.deleteAll();
        SparseArray<Speciality> specialities = new SparseArray<>();

        Integer index = 0;
        for (Employer employer : items) {
            employer.id = index;
            index++;
            if (employer.speciality != null) {
                for (Speciality speciality : employer.speciality) {
                    specialities.put(speciality.id, speciality);
                }
            }
        }
        specialityDao.insertAll(Utils.convertSparseToList(specialities));
        employerDao.insertAll(items);
    }

    public static void destroy() {
        INSTANCE = null;
    }
}