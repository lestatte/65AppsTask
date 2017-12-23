package ru.sample.elestatte.test65apps.activity;

/**
 * Interface which is describing methods for fragment's replacing in main activity.
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
@SuppressWarnings("unused")
public interface MainActivityDelegate {

    int START = 0;
    int EMPLOYERS = 1;
    int USER_INFO = 2;

    void replaceFragmentById(int id);

}
