package com.monu.newsapp;

import com.monu.newsapp.parameter.Headline;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interface {
    @GET("top-headlines")
    Call<Headline> getHeadlines(
      @Query("country") String country,
              @Query("category") String category,
                      @Query("apiKey") String apiKey
        );
}
