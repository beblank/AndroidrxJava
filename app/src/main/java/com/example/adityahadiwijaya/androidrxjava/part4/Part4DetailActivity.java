package com.example.adityahadiwijaya.androidrxjava.part4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.adityahadiwijaya.androidrxjava.R;
import com.example.adityahadiwijaya.androidrxjava.part4.utils.GitHubRepoAdapter;
import com.example.adityahadiwijaya.androidrxjava.part4.utils.GitHubUsersAdapter;
import com.example.adityahadiwijaya.androidrxjava.part4.utils.GitHubWrapper;
import com.example.adityahadiwijaya.androidrxjava.part4.utils.OnRVClickListener;

/**
 * Created by adityahadiwijaya on 12/19/16.
 */

public class Part4DetailActivity extends AppCompatActivity{

    private final static String TAG = "Dodol";
    private final static String USER_KEY = "user";
    private RecyclerView reposList;
    private String requestedUser;

    public static Intent from (Context context, String username){
        Intent intent = new Intent(context, Part4DetailActivity.class);
        intent.putExtra(USER_KEY, username);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part4_detail);

        requestedUser = getIntent().getStringExtra(USER_KEY);
        setTitle(requestedUser);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        GitHubRepoAdapter adapter = new GitHubRepoAdapter();
        GitHubWrapper.getReposForUsersInto(requestedUser, adapter);

        reposList = (RecyclerView)findViewById(R.id.reposList);
        reposList.setLayoutManager(layoutManager);
        reposList.setAdapter(adapter);
    }
}
