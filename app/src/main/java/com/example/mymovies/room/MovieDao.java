package com.example.mymovies.room;

import com.example.mymovies.models.Movie;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNewMovies(List<Movie> movies);

    @Query("DELETE from movie_table")
    void deleteAllMovies();

    @Query("SELECT * FROM movie_table")
    List<Movie> getMoviesSync();

}
