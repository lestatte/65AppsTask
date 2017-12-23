package ru.sample.elestatte.test65apps.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.functions.Consumer;
import ru.sample.elestatte.test65apps.R;
import ru.sample.elestatte.test65apps.activity.MainActivityDelegate;
import ru.sample.elestatte.test65apps.utility.Utils;
import ru.sample.elestatte.test65apps.viewmodel.MainViewModel;
import ru.sample.elestatte.test65apps.viewmodel.ViewModelState;

/**
 * Fragment for data loading process (appears on startup).
 *
 * @author Shramko Alexey
 *         Date: 19.12.17
 */
public class MainActivityFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ViewModelProviders.of(this).get(MainViewModel.class)
                .getStateForSubscription().subscribe(
                new Consumer<ViewModelState>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull ViewModelState r)
                            throws Exception {
                        switch (r) {
                            case READY:
                                Utils.replaceFragmentById(
                                        getActivity(), MainActivityDelegate.EMPLOYERS);
                                break;

                            case LOADING:
                                break;

                            case EMPTY:
                                break;

                            case ERROR:
                                break;
                        }
                    }
                }
        );
        return inflater.inflate(R.layout.ftragment_main_activity, container, false);
    }

}
