package ru.sample.elestatte.test65apps.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewAnimator;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

    @BindView(R.id.view_animator)
    ViewAnimator mViewAnimator;

    @BindView(R.id.repeat_button)
    Button mRepeatButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.ftragment_main_activity, container, false);
        ButterKnife.bind(this, view);
        final MainViewModel viewModel =
                ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getStateForSubscription().observeOn(AndroidSchedulers.mainThread()).subscribe(
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
                            case EMPTY:
                            case ERROR:
                                mViewAnimator.setDisplayedChild(r.ordinal());
                                break;
                        }
                    }
                }
        );
        mRepeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.repeatLoadData();
            }
        });
        return view;
    }

}
