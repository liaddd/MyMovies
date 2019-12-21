package com.example.mymovies.utils;

import android.app.Application;

public class MyMovieApplication extends Application {

    private static MyMovieApplication instance;

    public static synchronized MyMovieApplication getInstance() {
        if (instance == null){
            instance = new MyMovieApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
