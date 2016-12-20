package com.example.adityahadiwijaya.androidrxjava.part4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.adityahadiwijaya.androidrxjava.R;
import com.example.adityahadiwijaya.androidrxjava.part4.utils.GitHubUsersAdapter;
import com.example.adityahadiwijaya.androidrxjava.part4.utils.GitHubWrapper;
import com.example.adityahadiwijaya.androidrxjava.part4.utils.OnRVClickListener;

/**
 * Created by adityahadiwijaya on 12/19/16.
 */

public class Part4DetailActivity extends AppCompatActivity implements OnRVClickListener {

    private final static String TAG = "Dodol";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part4);

        GitHubUsersAdapter adapter = new GitHubUsersAdapter(this);
        GitHubWrapper.getUsersInto(adapter);
        recyclerView = (RecyclerView)findViewById(R.id.usersList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(String username) {
        Log.i(TAG, "onItemClicked: ");
    }
}
