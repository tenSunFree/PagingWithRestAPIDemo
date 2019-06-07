package com.home.pagingwithrestapidemo.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubService {

    private static final String BASE_URL = "https://api.github.com";

    public static GitHubApi createGitHubService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL);
        return builder.build().create(GitHubApi.class);
    }
}
