package com.example.stackoverflowusersapp;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StackOverflowServiceManager {

    private static StackOverflowServiceManager manager = null;

    private final boolean webserviceDebugging = true;
    StackOverflowService service = null;


    private StackOverflowServiceManager() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit.Builder retBuilder = new Retrofit.Builder()
                .baseUrl(StackOverflowService.URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        if (webserviceDebugging) retBuilder.client(client);
        service = retBuilder.build().create(StackOverflowService.class);
    }

    public static StackOverflowServiceManager getInstance() {
        if (manager == null) manager = new StackOverflowServiceManager();
        return manager;
    }

    public Observable<List<User>> getPopularUsersFromStackOverflow() {

        return service
                .getPopularUsers(100)
                .map(UserResponse::getUsers)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}