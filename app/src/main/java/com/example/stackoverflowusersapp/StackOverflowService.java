package com.example.stackoverflowusersapp;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface StackOverflowService {


    String URL = "https://api.stackexchange.com/";

    @GET("2.2/users?order=desc&sort=reputation&site=stackoverflow")
    Observable<UserResponse> getPopularUsers(@Query("pagesize") int pageSize);
}
