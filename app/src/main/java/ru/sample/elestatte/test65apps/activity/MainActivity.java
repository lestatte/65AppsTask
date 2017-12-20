package ru.sample.elestatte.test65apps.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import ru.sample.elestatte.test65apps.R;
import ru.sample.elestatte.test65apps.viewmodel.MainViewModel;
import ru.sample.elestatte.test65apps.viewmodel.ViewModelState;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getStateForSubscription().subscribe(
                new Consumer<ViewModelState>() {
                    @Override
                    public void accept(@NonNull ViewModelState r)
                            throws Exception {
                        Log.d("111", r.name());
                    }
                }
        );
    }

}
