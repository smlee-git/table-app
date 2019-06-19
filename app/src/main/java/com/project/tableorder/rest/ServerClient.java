package com.project.tableorder.rest;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerClient {
    private static ServerClient instance;

    private static ServerClient getInstance() {
        if (instance == null) {
            instance = new ServerClient();
        }
        return instance;
    }

    private Retrofit retrofit;

    private ServerClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://149.28.29.135:4000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static <T> T getOrCreate(Class<T> tClass) {
        return getInstance().getOrCreateInternal(tClass);
    }

    private HashMap<Object, Object> serviceHashMap = new HashMap<>();

    private <T> T getOrCreateInternal(Class<T> tClass) {
        if (serviceHashMap.containsKey(tClass)) {
            return (T) serviceHashMap.get(tClass);
        }

        T service = retrofit.create(tClass);
        serviceHashMap.put(tClass, service);
        return service;
    }
}
