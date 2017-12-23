package ru.sample.elestatte.test65apps.components;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.SparseArray;

import java.util.ArrayList;
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
@Database(entities = { Employer.class, Speciality.class, EmployerSpecialityJoin.class },
          version = 1)
public abstract class EmployerDatabase extends RoomDatabase {

    private static EmployerDatabase INSTANCE = null;

    public abstract EmployerDao getEmployerDao();
    public abstract SpecialityDao getSpecialityDao();
    public abstract EmployerSpecialityDao getEmployerSpecialityDao();

    public static EmployerDatabase getInstance(Context context) {
        if (null == INSTANCE) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    EmployerDatabase.class, "employers-database").build();
        }
        return INSTANCE;
    }

    public void putData(List<Employer> items) {

        EmployerDao employerDao = getEmployerDao();
        SpecialityDao specialityDao = getSpecialityDao();
        EmployerSpecialityDao employerSpecialityDao = getEmployerSpecialityDao();

        employerDao.deleteAll();
        specialityDao.deleteAll();
        employerSpecialityDao.deleteAll();

        List<EmployerSpecialityJoin> employerSpecialityJoins = new ArrayList<>();
        SparseArray<Speciality> specialities = new SparseArray<>();

        Integer index = 0;
        for (Employer employer : items) {
            employer.id = index;
            index++;
            if (null != employer.speciality) {
                for (Speciality speciality : employer.speciality) {
                    employerSpecialityJoins.add(
                            new EmployerSpecialityJoin(employer.id, speciality.id));
                    specialities.put(speciality.id, speciality);
                }
            }
        }

        employerDao.insertAll(items);
        specialityDao.insertAll(Utils.convertSparseToList(specialities));
        employerSpecialityDao.insertAll(employerSpecialityJoins);
    }

    public static void destroy() {
        INSTANCE = null;
    }
}