package ru.sample.elestatte.test65apps.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import ru.sample.elestatte.test65apps.App65;
import ru.sample.elestatte.test65apps.components.IApiClient;
import ru.sample.elestatte.test65apps.components.database.EmployerDatabase;
import ru.sample.elestatte.test65apps.response.EmployersList;
import ru.sample.elestatte.test65apps.utility.PrefManager;
import ru.sample.elestatte.test65apps.utility.Utils;

/**
 * Main app view model
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
public class MainViewModel extends AndroidViewModel {

    private Disposable mDataDisposable = null;
    private BehaviorSubject<ViewModelState> mCurrentState =
            BehaviorSubject.createDefault(ViewModelState.LOADING);

    @Inject
    IApiClient mApi;

    public MainViewModel(@android.support.annotation.NonNull Application application) {
        super(application);
        App65.getAppComponent().inject(this);
        loadData();
    }

    public void repeatLoadData() {
        mCurrentState.onNext(ViewModelState.LOADING);
        loadData();
    }

    private void loadData() {
        if (null != mDataDisposable) {
            mDataDisposable.dispose();
        }
        final Context context = getApplication().getApplicationContext();
        mDataDisposable = mApi.fetchEmployers()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Consumer<EmployersList>() {
                            @Override
                            public void accept(@NonNull EmployersList r) throws Exception {
                                String newCheckSum = Utils.getChecksum(r);
                                if (!PrefManager.readChecksum(context).equals(newCheckSum)) {
                                    PrefManager.writeChecksum(context, newCheckSum);
                                    EmployerDatabase.getInstance(context).putData(r.items);
                                }
                                if (r.items.isEmpty()) {
                                    mCurrentState.onNext(ViewModelState.EMPTY);
                                } else {
                                    mCurrentState.onNext(ViewModelState.READY);
                                }
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                mCurrentState.onNext(ViewModelState.ERROR);
                            }
                        });
    }

    public Observable<ViewModelState> getStateForSubscription() {
        return mCurrentState;
    }

    @Override
    protected void onCleared() {
        if (null != mDataDisposable) {
            mDataDisposable.dispose();
        }
        super.onCleared();
    }
}