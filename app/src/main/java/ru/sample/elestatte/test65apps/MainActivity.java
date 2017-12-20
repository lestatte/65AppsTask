package ru.sample.elestatte.test65apps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ru.sample.elestatte.test65apps.components.ApiClient;
import ru.sample.elestatte.test65apps.response.EmployersList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiClient.fetchEmployers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    new Consumer<EmployersList>() {
                        @Override
                        public void accept(@NonNull EmployersList r)
                                throws Exception {
                            Log.d("111", "count = " + r.items.size());
                        }
                    },
                    new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable)
                                throws Exception {
                            Log.d("111", throwable.toString());
                        }
                    });
    }
}
