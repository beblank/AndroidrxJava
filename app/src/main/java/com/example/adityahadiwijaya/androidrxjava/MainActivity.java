package com.example.adityahadiwijaya.androidrxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.adityahadiwijaya.androidrxjava.part4.Part4Activity;
import com.example.adityahadiwijaya.androidrxjava.part1.Part1Activity;
import com.example.adityahadiwijaya.androidrxjava.part2.Part2Activity;
import com.example.adityahadiwijaya.androidrxjava.part3.Part3Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireUI();
    }

    private void wireUI(){
        setTapListener(R.id.btnPart1);
        setTapListener(R.id.btnPart2);
        setTapListener(R.id.btnPart3);
        setTapListener(R.id.btnPart4);
    }

    private void setTapListener(int viewId){
        findViewById(viewId).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        startActivity(getLaunchIntentFor(v.getId()));
    }

    private Intent getLaunchIntentFor(int viewId){
        Intent launchIntent = null;

        switch (viewId){
            case R.id.btnPart1:
                launchIntent = new Intent(this, Part1Activity.class);
                break;
            case R.id.btnPart2:
                launchIntent = new Intent(this, Part2Activity.class);
                break;
            case R.id.btnPart3:
                launchIntent = new Intent(this, Part3Activity.class);
                break;
            case R.id.btnPart4:
                launchIntent = new Intent(this, Part4Activity.class);
                break;
        }


        return launchIntent;
    }
}
