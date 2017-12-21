package ru.sample.elestatte.test65apps.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import ru.sample.elestatte.test65apps.components.ApiClient;
import ru.sample.elestatte.test65apps.components.EmployerDao;
import ru.sample.elestatte.test65apps.components.EmployerDatabase;
import ru.sample.elestatte.test65apps.response.EmployersList;

/**
 * Main app view model
 *
 * @author Shramko Alexey
 *         Date: 20.12.17
 */
public class MainViewModel extends AndroidViewModel {

    private Disposable mDataDisposable = null;
    private BehaviorSubject<ViewModelState> mCurrentState =
            BehaviorSubject.createDefault(ViewModelState.LOADING);

    public MainViewModel(@android.support.annotation.NonNull Application application) {
        super(application);
        loadData();
    }

    private void loadData() {
        if (mDataDisposable != null) {
            mDataDisposable.dispose();
        }
        final Context context = getApplication().getApplicationContext();
        mDataDisposable = ApiClient.fetchEmployers()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Consumer<EmployersList>() {
                            @Override
                            public void accept(@NonNull EmployersList r)
                                    throws Exception {
                                EmployerDao employerDao =
                                        EmployerDatabase.getInstance(context).getEmployerDao();
                                employerDao.insertAll(r.items);
                                mCurrentState.onNext(ViewModelState.READY);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable)
                                    throws Exception {
                                mCurrentState.onNext(ViewModelState.ERROR);
                            }
                        });
    }

    public Observable<ViewModelState> getStateForSubscription() {
        return mCurrentState;
    }

    @Override
    protected void onCleared() {
        if (mDataDisposable != null) {
            mDataDisposable.dispose();
        }
        super.onCleared();
    }
}