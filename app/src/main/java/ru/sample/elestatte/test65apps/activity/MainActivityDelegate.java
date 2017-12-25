package ru.sample.elestatte.test65apps.activity;

import android.support.v4.app.Fragment;

/**
 * Interface which is describing methods for fragment's replacing in main activity.
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
public interface MainActivityDelegate {

    int START = 0;
    int EMPLOYERS = 1;

    void replaceFragmentById(int id);
    void addFragment(Fragment fragment);
}
