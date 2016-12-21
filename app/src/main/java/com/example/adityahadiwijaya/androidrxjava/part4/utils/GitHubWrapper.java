package com.example.adityahadiwijaya.androidrxjava.part4.utils;

import com.example.adityahadiwijaya.androidrxjava.part4.model.GitHubRepo;
import com.example.adityahadiwijaya.androidrxjava.part4.model.GitHubUser;
import com.example.adityahadiwijaya.androidrxjava.part4.service.GitHubService;
import com.example.adityahadiwijaya.androidrxjava.part4.service.ServiceFactory;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by adityahadiwijaya on 12/20/16.
 */

public class GitHubWrapper {

    private static final String[] interestingUsers={"beblank", "tiwiz", "rock3r", "dextorer", "Tachion"};


    public static void getUsersInto(final GitHubUsersAdapter adapter){

        final GitHubService gitHubService = ServiceFactory.createServiceFrom(GitHubService.class, GitHubService.ENDPOINT);

        Func1<String, Observable<GitHubUser>> flatMapUsers = new Func1<String, Observable<GitHubUser>>() {
            @Override
            public Observable<GitHubUser> call(String s) {
                return gitHubService.getUserData(s);
            }
        };

        Action1<GitHubUser> adapterAction = new Action1<GitHubUser>() {
            @Override
            public void call(GitHubUser gitHubUser) {
                adapter.addUser(gitHubUser);
            }
        };

        Observable.from(interestingUsers)
                .flatMap(flatMapUsers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapterAction);
    }

    public static void getReposForUsersInto(final String username, final GitHubRepoAdapter adapter){
        final GitHubService gitHubService = ServiceFactory.createServiceFrom(GitHubService.class, GitHubService.ENDPOINT);

        Func1 <GitHubRepo[], Observable<GitHubRepo>> flatMapRepo = new Func1<GitHubRepo[], Observable<GitHubRepo>>() {
            @Override
            public Observable<GitHubRepo> call(GitHubRepo[] gitHubRepos) {
                return Observable.from(gitHubRepos);
            }
        };

        Action1<GitHubRepo> adapterAction = new Action1<GitHubRepo>() {
            @Override
            public void call(GitHubRepo repo) {
                adapter.addRepo(repo);
            }
        };

        gitHubService.getRepoData(username)
                .flatMap(flatMapRepo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapterAction);
    }


}
