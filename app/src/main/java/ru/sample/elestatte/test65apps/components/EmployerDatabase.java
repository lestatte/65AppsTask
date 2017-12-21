package ru.sample.elestatte.test65apps.components;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ru.sample.elestatte.test65apps.response.Employer;

/**
 * Google Room database implementation
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
@SuppressWarnings("unused")
@Database(entities = { Employer.class }, version = 1)
public abstract class EmployerDatabase extends RoomDatabase {

    private static EmployerDatabase INSTANCE = null;

    public abstract EmployerDao getEmployerDao();

    public static EmployerDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    EmployerDatabase.class, "employers-database").build();
        }
        return INSTANCE;
    }

    public static void destroy() {
        INSTANCE = null;
    }
}