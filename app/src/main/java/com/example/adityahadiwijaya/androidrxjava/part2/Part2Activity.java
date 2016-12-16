package com.example.adityahadiwijaya.androidrxjava.part2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adityahadiwijaya.androidrxjava.R;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class Part2Activity extends AppCompatActivity {

    TextView txtPart1;
    Context context;

    Action1<String> textViewOnNextAction = new Action1<String>(){
        @Override
        public void call(String s) {
            txtPart1.setText(s);
        }
    };

    Func1<String, String> toUpperCaseMap = new Func1<String, String>(){
        @Override
        public String call(String s) {
            return s.toUpperCase();
        }
    };

    Action1<String> toastOnNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);
        txtPart1 = (TextView)findViewById(R.id.txtPart1);
        context = this;


        Observable<String> singleObservable = Observable.just("Hello, World!");

        singleObservable.observeOn(AndroidSchedulers.mainThread())
                .map(toUpperCaseMap)
                .subscribe(textViewOnNextAction);
    }
}
