package ru.sample.elestatte.test65apps.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import ru.sample.elestatte.test65apps.R;
import ru.sample.elestatte.test65apps.adapter.EmployerAdapter;
import ru.sample.elestatte.test65apps.adapter.SpecialityAdapter;
import ru.sample.elestatte.test65apps.response.Employer;
import ru.sample.elestatte.test65apps.response.Speciality;
import ru.sample.elestatte.test65apps.utility.Utils;
import ru.sample.elestatte.test65apps.viewmodel.FilteredEmployerListViewModel;

/**
 * Fragment for displaying list of employers (appears after startup fragment).
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
public class FilteredEmployerListFragment extends Fragment {

    @BindView(R.id.employer_list)
    ListView mEmployerList;

    @BindView(R.id.speciality_list)
    Spinner mSpecialityList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_filtered_employer_list, container, false);
        ButterKnife.bind(this, view);
        final FilteredEmployerListViewModel viewModel =
                ViewModelProviders.of(this).get(FilteredEmployerListViewModel.class);
        viewModel.getSpecialityDataForSubscription()
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                    new Consumer<List<Speciality>>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull List<Speciality> r)
                                throws Exception {
                            mSpecialityList.setAdapter(new SpecialityAdapter(getContext(), r));
                            mSpecialityList.setOnItemSelectedListener(
                                    new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(
                                        AdapterView<?> adapterView, View view, int i, long l) {
                                    Speciality item = (Speciality) adapterView.getSelectedItem();
                                    if (null != item) {
                                        viewModel.loadEmployersBySpeciality(item.id);
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {
                                    viewModel.loadEmployersBySpeciality(-1);
                                }
                            });
                        }
                    }
                );
        viewModel.getEmployerDataForSubscription()
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                    new Consumer<List<Employer>>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull List<Employer> r)
                                throws Exception {
                            Context context = getContext();
                            if (null != context) {
                                mEmployerList.setAdapter(new EmployerAdapter(context, r));
                                mEmployerList.setOnItemClickListener(
                                        new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(
                                            AdapterView<?> adapterView, View view, int i, long l) {
                                        Employer item =
                                                (Employer) adapterView.getItemAtPosition(i);
                                        if (null != item) {
                                            viewModel.loadSpecialitiesByEmployer(item);
                                        }
                                    }
                                });
                            }
                        }
                    }
        );
        viewModel.getFullEmployerForSubscription()
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                    new Consumer<Employer>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull Employer r)
                                throws Exception {
                            Utils.addFragment(getActivity(),
                                    DetailEmployerFragment.create(r));
                        }
                    }
        );
        return view;
    }

}
