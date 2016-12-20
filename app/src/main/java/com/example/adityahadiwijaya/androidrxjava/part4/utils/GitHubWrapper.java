package com.example.adityahadiwijaya.androidrxjava.part4.utils;

import com.example.adityahadiwijaya.androidrxjava.part4.model.GitHubUser;
import com.example.adityahadiwijaya.androidrxjava.part4.service.GitHubService;
import com.example.adityahadiwijaya.androidrxjava.part4.service.ServiceFactory;

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


}
