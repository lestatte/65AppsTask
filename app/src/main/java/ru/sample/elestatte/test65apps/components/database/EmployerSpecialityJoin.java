package ru.sample.elestatte.test65apps.components.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import ru.sample.elestatte.test65apps.response.Employer;
import ru.sample.elestatte.test65apps.response.Speciality;

/**
 * Employer - Specialty item for mapping
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
@Entity(tableName = "employer_speciality_join",
        primaryKeys = { "idEmployer", "idSpeciality" },
        indices = { @Index(value = "idSpeciality") },
        foreignKeys = {
                @ForeignKey(entity = Employer.class,
                        parentColumns = "id",
                        childColumns = "idEmployer"),
                @ForeignKey(entity = Speciality.class,
                        parentColumns = "id",
                        childColumns = "idSpeciality")
        })
public class EmployerSpecialityJoin {

    public final int idEmployer;
    public final int idSpeciality;

    public EmployerSpecialityJoin(final int idEmployer, final int idSpeciality) {
        this.idEmployer = idEmployer;
        this.idSpeciality = idSpeciality;
    }

}