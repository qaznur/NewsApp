package com.tutorial.nura.newsapp.rest;

import com.tutorial.nura.newsapp.models.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("everything")
    Call<NewsModel> getNews(@Query("sources") String sources, @Query("apiKey") String apiKey);

}
