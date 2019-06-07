package com.home.pagingwithrestapidemo.http;

import com.home.pagingwithrestapidemo.data.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApi {

    @GET("/users")
    Call<List<UserData>> getUser(@Query("since") long since, @Query("per_page") int perPage);
}
