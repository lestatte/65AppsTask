package ru.sample.elestatte.test65apps.components;

import java.util.List;

import ru.sample.elestatte.test65apps.response.Employer;
import ru.sample.elestatte.test65apps.response.Speciality;

/**
 * Interface for interaction with local storage
 *
 * @author Shramko Alexey
 *         Date: 05.02.18
 */
public interface IEmployerRepository {

    void putData(List<Employer> items);
    List<Employer> getEmployers();
    List<Employer> getEmployerForSpeciality(final int idSpeciality);
    List<Speciality> getSpecialityForEmployer(final int idEmployer);
    List<Speciality> getSpecialities();

}
