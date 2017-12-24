package ru.sample.elestatte.test65apps.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import ru.sample.elestatte.test65apps.R;
import ru.sample.elestatte.test65apps.components.EmployerDatabase;
import ru.sample.elestatte.test65apps.response.Employer;
import ru.sample.elestatte.test65apps.response.Speciality;

/**
 * View model for fragment where we display list of employers with filter
 *
 * @author Shramko Alexey
 *         Date: 20.12.17
 */
public class FilteredEmployerListViewModel extends AndroidViewModel {

    private Disposable mSpecialityDataDisposable = null;
    private Disposable mEmployerDataDisposable = null;
    private BehaviorSubject<List<Speciality>> mSpecialityData = BehaviorSubject.create();
    private BehaviorSubject<List<Employer>> mEmployersData = BehaviorSubject.create();

    public FilteredEmployerListViewModel(
            @android.support.annotation.NonNull Application application) {
        super(application);
        loadData();
    }

    private void loadData() {
        if (null != mSpecialityDataDisposable) {
            mSpecialityDataDisposable.dispose();
        }
        final Context context = getApplication().getApplicationContext();
        mSpecialityDataDisposable =
                Observable.fromCallable(new Callable<List<Speciality>>() {
                    @Override public List<Speciality> call() {
                        return EmployerDatabase.getInstance(context).getSpecialityDao().getAll();
                    }
                }).observeOn(Schedulers.io())
                  .subscribeOn(Schedulers.io())
                  .subscribe(
                          new Consumer<List<Speciality>>() {
                              @Override
                              public void accept(@NonNull List<Speciality> r) throws Exception {
                                  List<Speciality> data = new ArrayList<>();
                                  data.add(new Speciality(-1,
                                          context.getString(R.string.all_speciality)));
                                  data.addAll(r);
                                  mSpecialityData.onNext(data);
                              }
                          });
    }

    public Observable<List<Speciality>> getSpecialityDataForSubscription() {
        return mSpecialityData;
    }
    public Observable<List<Employer>> getEmployerDataForSubscription() {
        return mEmployersData;
    }

    public void loadEmployersBySpeciality(final int id) {
        if (null != mEmployerDataDisposable) {
            mEmployerDataDisposable.dispose();
        }
        final Context context = getApplication().getApplicationContext();
        mEmployerDataDisposable =
                Observable.fromCallable(new Callable<List<Employer>>() {
                    @Override public List<Employer> call() {
                        if (id == -1) {
                            return EmployerDatabase.getInstance(context).
                                    getEmployerDao().getAll();
                        } else {
                            return EmployerDatabase.getInstance(context).
                                    getEmployerSpecialityDao().getEmployerForSpeciality(id);
                        }
                    }
                }).observeOn(Schedulers.io())
                  .subscribeOn(Schedulers.io())
                  .subscribe(
                          new Consumer<List<Employer>>() {
                              @Override
                              public void accept(@NonNull List<Employer> r) throws Exception {
                                  mEmployersData.onNext(r);
                              }
                          });
    }

    @Override
    protected void onCleared() {
        if (null != mSpecialityDataDisposable) {
            mSpecialityDataDisposable.dispose();
        }
        if (null != mEmployerDataDisposable) {
            mEmployerDataDisposable.dispose();
        }
        super.onCleared();
    }
}
