package com.example.adityahadiwijaya.androidrxjava.part4.service;

import com.example.adityahadiwijaya.androidrxjava.part4.model.GitHubRepo;
import com.example.adityahadiwijaya.androidrxjava.part4.model.GitHubUser;

import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by adityahadiwijaya on 12/20/16.
 */

public interface GitHubService {

    String ENDPOINT = "https://api.github.com";

    @GET("/users/{user}")
    Observable<GitHubUser> getUserData(@Path("user") String user);

    @GET("/users/{user}/repos")
    Observable<GitHubRepo[]> getRepoData(@Path("user") String user);

}

