package ru.sample.elestatte.test65apps.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.sample.elestatte.test65apps.R;

/**
 * Fragment for displaying list of employers (appears after startup fragment).
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
public class FilteredEmployerListFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_filtered_employer_list, container, false);
    }

}
