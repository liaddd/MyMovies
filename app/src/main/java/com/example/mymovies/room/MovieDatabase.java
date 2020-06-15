package com.example.mymovies.room;

import android.content.Context;

import com.example.mymovies.R;
import com.example.mymovies.models.Movie;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Movie.class, version = 2, exportSchema = false)

public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;
    public abstract MovieDao movieDao();

    /**
     * @return A new instance of MovieDatabase.
     */
    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDatabase.class, context.getString(R.string.movies_database))
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
