package ru.sample.elestatte.test65apps.components;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.sample.elestatte.test65apps.components.database.EmployerDao;
import ru.sample.elestatte.test65apps.components.database.EmployerDatabase;
import ru.sample.elestatte.test65apps.components.database.EmployerSpecialityDao;
import ru.sample.elestatte.test65apps.components.database.EmployerSpecialityJoin;
import ru.sample.elestatte.test65apps.components.database.SpecialityDao;
import ru.sample.elestatte.test65apps.response.Employer;
import ru.sample.elestatte.test65apps.response.Speciality;
import ru.sample.elestatte.test65apps.utility.Utils;

/**
 * DB agent implementation (Room)
 *
 * @author Shramko Alexey
 *         Date: 06.02.18
 */
@Singleton
public class EmployerRepositoryImpl implements IEmployerRepository {

    @NonNull
    private EmployerDatabase mRoom;

    @Inject
    EmployerRepositoryImpl(Context context) {
        mRoom = Room.databaseBuilder(context.getApplicationContext(),
                EmployerDatabase.class, "employers-database").build();
    }

    @Override
    public void putData(List<Employer> items) {
        EmployerDao employerDao = mRoom.getEmployerDao();
        SpecialityDao specialityDao = mRoom.getSpecialityDao();
        EmployerSpecialityDao employerSpecialityDao = mRoom.getEmployerSpecialityDao();

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

    @Override
    public List<Employer> getEmployers() {
        return mRoom.getEmployerDao().getAll();
    }

    @Override
    public List<Employer> getEmployerForSpeciality(final int idSpeciality) {
        return mRoom.getEmployerSpecialityDao().getEmployerForSpeciality(idSpeciality);
    }

    @Override
    public List<Speciality> getSpecialityForEmployer(final int idEmployer) {
        return mRoom.getEmployerSpecialityDao().getSpecialityForEmployer(idEmployer);
    }

    @Override
    public List<Speciality> getSpecialities() {
        return mRoom.getSpecialityDao().getAll();
    }

}
