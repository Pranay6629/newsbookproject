package com.monu.newsapp;

import com.monu.newsapp.parameter.Books;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Books_Client {
    @GET("volumes")
    Call<Books> getBooks(
            @Query("q") String q,
            @Query("apiKey") String apiKey
    );


}
