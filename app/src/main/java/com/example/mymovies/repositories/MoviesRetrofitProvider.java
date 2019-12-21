package com.example.mymovies.repositories;

import com.example.mymovies.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class MoviesRetrofitProvider {

    private static final MoviesRetrofitProvider instance = new MoviesRetrofitProvider();


    /**
     * @return A new instance of MoviesRetrofitProvider.
     */

    static MoviesRetrofitProvider getInstance() {
        return instance;
    }

    private Retrofit moviesRetrofit = new Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Retrofit getMoviesRetrofit() {
        return moviesRetrofit;
    }

}
