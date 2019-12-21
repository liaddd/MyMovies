package com.example.mymovies.server_connection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebRequests {

    @GET("discover/movie")
    Call<MovieResponse> getMovies(@Query("page") int requiredPage, @Query("api_key") String apiKey);
}
