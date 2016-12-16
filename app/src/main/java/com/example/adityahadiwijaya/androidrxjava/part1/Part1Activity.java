package com.example.adityahadiwijaya.androidrxjava.part1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.adityahadiwijaya.androidrxjava.R;

import org.w3c.dom.Text;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class Part1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);

        final TextView txtPart1 = (TextView)findViewById(R.id.txtPart1);

        Observable.OnSubscribe observableAction = new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello, World!");
                subscriber.onCompleted();
            }
        };
        Observable<String> observable = Observable.create(observableAction);

        Subscriber<String> textViewSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                txtPart1.setText(s);
            }
        };


        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(textViewSubscriber);
    }

}
