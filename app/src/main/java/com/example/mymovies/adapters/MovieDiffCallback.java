package com.example.mymovies.adapters;

import com.example.mymovies.models.Movie;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class MovieDiffCallback extends DiffUtil.Callback {

    private List<Movie> oldMovies;
    private List<Movie> newMovies;


    public MovieDiffCallback(List<Movie> oldMovies, List<Movie> newMovies) {
        this.oldMovies = oldMovies;
        this.newMovies = newMovies;
    }

    @Override
    public int getOldListSize() {
        return oldMovies.size();
    }

    @Override
    public int getNewListSize() {
        return newMovies.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMovies.get(oldItemPosition) == newMovies.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldMovies.get(oldItemPosition).equals(newMovies.get(newItemPosition));
    }
}
