package com.example.adityahadiwijaya.androidrxjava.part5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.adityahadiwijaya.androidrxjava.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by adityahadiwijaya on 12/19/16.
 */

public class Part5Activity extends AppCompatActivity {

    private TextView textEmittedNumber;
    private static final String TAG = "NightObserver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5);
        wireUpActivity();
        startEmitting();
    }

    private void wireUpActivity() {
        textEmittedNumber = (TextView)findViewById(R.id.mainEmittedNumber);
    }

    private void startEmitting() {
        Log.d(TAG, "Night gathers, and now my watch begins");
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this.logOnNext, this.logOnError, this.logOnComplete);
    }

    Action1<Long> logOnNext = new Action1<Long>() {
        @Override
        public void call(Long time) {
            textEmittedNumber.setText(String.valueOf(time));
            Log.d(TAG, "Nothing bad happened for " + time + " seconds");
        }
    };

    Action1<Throwable> logOnError = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            Log.e(TAG, "Something worse than White Walkers is approaching!/t" + throwable.getMessage() );
        }
    };

    Action0 logOnComplete = new Action0() {
        @Override
        public void call() {
            Log.d(TAG, "The day has come, may my watch end!");
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "The sun is rising!");
    }
}
