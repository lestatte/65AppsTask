package ru.sample.elestatte.test65apps.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import ru.sample.elestatte.test65apps.R;
import ru.sample.elestatte.test65apps.fragment.FilteredEmployerListFragment;
import ru.sample.elestatte.test65apps.fragment.MainActivityFragment;

/**
 * Main and single activity in app.
 * Here we replace fragments and init loading from server
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
public class MainActivity extends AppCompatActivity implements MainActivityDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == savedInstanceState) {
            replaceFragmentById(MainActivityDelegate.START);
        }
    }

    @Override
    public void replaceFragmentById(int id) {
        Fragment fragment = null;
        switch (id) {
            case MainActivityDelegate.START:
                fragment = new MainActivityFragment();
                break;

            case MainActivityDelegate.EMPLOYERS:
                fragment = new FilteredEmployerListFragment();
                break;

            default:
                break;
        }
        if (null != fragment) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.container, fragment, fragment.getClass().getSimpleName()).commit();
        }
    }
}
