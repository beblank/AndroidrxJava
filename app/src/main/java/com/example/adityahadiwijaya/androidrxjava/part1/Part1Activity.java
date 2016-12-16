package com.example.adityahadiwijaya.androidrxjava.part1;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adityahadiwijaya.androidrxjava.R;

import org.w3c.dom.Text;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class Part1Activity extends AppCompatActivity {

    TextView txtPart1;
    Context context;

    Observable.OnSubscribe observableAction = new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello, World!");
            subscriber.onCompleted();
        }
    };

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

    Subscriber<String> toastSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(String s) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    };

    Action1<String> textViewOnNextAction = new Action1<String>(){
        @Override
        public void call(String s) {
            txtPart1.setText(s);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);

        txtPart1 = (TextView)findViewById(R.id.txtPart1);
        context = this;

        Observable<String> observable = Observable.create(observableAction);

        observable.observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(textViewSubscriber);
        observable.subscribe(toastSubscriber);
    }

}
