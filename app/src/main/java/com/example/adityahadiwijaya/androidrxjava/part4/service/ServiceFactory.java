package com.example.adityahadiwijaya.androidrxjava.part4.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by adityahadiwijaya on 12/20/16.
 */

public class ServiceFactory {
    public  static <T> T createServiceFrom(final Class<T> serviceClass, String endpoint){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        T service = retrofit.create(serviceClass);

        return service;
    }
}
