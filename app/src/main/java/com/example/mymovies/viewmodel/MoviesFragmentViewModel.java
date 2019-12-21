package com.example.mymovies.viewmodel;

import com.example.mymovies.models.Movie;
import com.example.mymovies.repositories.MovieRepository;
import com.example.mymovies.utils.Resource;

import java.util.List;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import static com.example.mymovies.utils.Constants.MOVIES_IN_PAGE;

public class MoviesFragmentViewModel extends ViewModel {

    private int currentPageNumber = 0;
    private MovieRepository repository;


    public MoviesFragmentViewModel(){
        repository = MovieRepository.getInstance();
    }

    public LiveData<Resource<List<Movie>>> getAllMovies() {
        if (currentPageNumber == 0) {
            getNextPage();
        }

        return Transformations.map(repository.getMoviesLiveData(), new Function<Resource<List<Movie>>, Resource<List<Movie>>>() {
            @Override
            public Resource<List<Movie>> apply(Resource<List<Movie>> input) {
                if (input != null && input.data != null)
                    currentPageNumber = input.data.size() / MOVIES_IN_PAGE;
                return input;
            }
        });
    }

    public void getNextPage() {
        repository.fetchAnotherPage(++currentPageNumber);
    }


}
