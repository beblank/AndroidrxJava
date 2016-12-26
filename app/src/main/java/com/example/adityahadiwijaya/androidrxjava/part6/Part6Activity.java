package com.example.adityahadiwijaya.androidrxjava.part6;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adityahadiwijaya.androidrxjava.R;
import com.jakewharton.rxbinding.support.design.widget.RxSnackbar;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import rx.functions.Action;
import rx.functions.Action1;

/**
 * Created by adityahadiwijaya on 12/19/16.
 */

public class Part6Activity extends AppCompatActivity {

    TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part6);
        responseTextView = (TextView)findViewById(R.id.response);

        manageToolbar();
        manageFloatingActionButton();
        manageEditTexts();
    }

    private void manageToolbar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RxToolbar.itemClicks(toolbar).subscribe(onToolbarItemClicked);
        RxToolbar.navigationClicks(toolbar).subscribe();
    }

    Action1<MenuItem> onToolbarItemClicked = new Action1<MenuItem>() {
        @Override
        public void call(MenuItem menuItem) {
            String message = "Item \"" + menuItem.getTitle() + "\" clicked";
            Toast.makeText(Part6Activity.this, message, Toast.LENGTH_SHORT).show();
        }
    };



    private void onToolbarNavigationClicked1(){
        Toast.makeText(this, "Navigation item clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.part6, menu);
        return true;
    }

    private void manageFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        RxView.clicks(fab).subscribe();
    }

    private void onFabClicked(){
        Snackbar testSnackbar = Snackbar.make(findViewById(android.R.id.content), "Snackbar clicked", Snackbar.LENGTH_SHORT);
        testSnackbar.show();

        RxSnackbar.dismisses(testSnackbar).subscribe(onScnackbarDismissed);
    }

    Action1<Integer> onScnackbarDismissed = new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
            String text = "Snackbar dissmised with code " + integer;
            Toast.makeText(Part6Activity.this, text, Toast.LENGTH_SHORT).show();
        }
    };

    private void manageEditTexts() {
        EditText usualApproachEditText, reactiveApproachEditText;

        usualApproachEditText = (EditText)findViewById(R.id.editTextUsualApproach);
        usualApproachEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                onNewTextChanged(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        reactiveApproachEditText = (EditText)findViewById(R.id.editTextReactiveApproach);
        RxTextView.textChanges(reactiveApproachEditText).subscribe(onNewTextChange);
    }

    private void onNewTextChanged(CharSequence text){
        responseTextView.setText(text);
    }

    Action1<CharSequence> onNewTextChange = new Action1<CharSequence>() {
        @Override
        public void call(CharSequence s) {
            responseTextView.setText(s);
        }
    };

}
