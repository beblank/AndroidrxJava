package com.example.adityahadiwijaya.androidrxjava.part2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adityahadiwijaya.androidrxjava.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class Part2Activity extends AppCompatActivity {

    TextView txtPart1;
    Context context;

    final String[] manyWords = {"Hello", "to", "everyone", "from", "RxAndroid",
            "something", "that", "is", "really", "nice"};

    final List<String> manyWordList = Arrays.asList(manyWords);

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

    Func1<List<String>, Observable<String>> getUrls = new Func1<List<String>, Observable<String>>() {
        @Override
        public Observable<String> call(List<String> strings) {
            return Observable.from(strings);
        }
    };

    Func2<String, String, String> mergeRoutine = new Func2<String, String, String>() {
        @Override
        public String call(String s, String s1) {
            return String.format("%s %s",s,s1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);
        txtPart1 = (TextView)findViewById(R.id.txtPart1);
        context = this;


        Observable<String> singleObservable = Observable.just("Hello, World!");

        Observable<String> oneByOneObservable = Observable.from(manyWords);

        singleObservable.observeOn(AndroidSchedulers.mainThread())
                .map(toUpperCaseMap)
                .subscribe(textViewOnNextAction);

//        oneByOneObservable.observeOn(AndroidSchedulers.mainThread())
//                .map(toUpperCaseMap)
//                .subscribe(toastOnNextAction);

        Observable.just(manyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(getUrls)
                .reduce(mergeRoutine)
                .subscribe(toastOnNextAction);
    }
}
