package com.example.mymovies.repositories;

import android.os.AsyncTask;

import com.example.mymovies.R;
import com.example.mymovies.models.Movie;
import com.example.mymovies.room.MovieDao;
import com.example.mymovies.room.MovieDatabase;
import com.example.mymovies.server_connection.MovieResponse;
import com.example.mymovies.server_connection.WebRequests;
import com.example.mymovies.utils.MyMovieApplication;
import com.example.mymovies.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mymovies.utils.Constants.MOVIES_IN_PAGE;


public class MovieRepository {

    private static final String EMPTY_BODY_FROM_WS = "Empty body from ws.";
    private static final String UNKNOWN_ERROR =  "Unknown error.";
    private MovieDao movieDao;
    private MutableLiveData<Resource<List<Movie>>> moviesLiveData = new MutableLiveData<>();
    private List<Movie> movies = new ArrayList<>();
    private WebRequests webRequests;
    private static MovieRepository instance;

    /**
     * @return A new instance of MovieRepository.
     */

    
    public static synchronized MovieRepository getInstance() {
        if (instance == null){
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        MovieDatabase database = MovieDatabase.getInstance(MyMovieApplication.getInstance());
        movieDao = database.movieDao();
        webRequests = MoviesRetrofitProvider.getInstance().getMoviesRetrofit().create(WebRequests.class);
    }

    private void saveAllMoviesInDatabase(List<Movie> movies) {
        new saveAllMoviesAsyncTask(movieDao).execute(movies);
    }

    public LiveData<Resource<List<Movie>>> getMoviesLiveData() {
        return moviesLiveData;
    }

    public void fetchAnotherPage(int pageNumber) {
        // fetch from heap
        if (movies != null && movies.size() > 0 && (movies.size() / MOVIES_IN_PAGE) >= pageNumber) {
            return; // this data is already inside moviesLiveData.
        }
        fetchFromDb(pageNumber);
    }

    private void updateSuccessMoviesToLiveData() {
        moviesLiveData.postValue(Resource.success(movies));
    }

    private void updateErrorMoviesToLiveData(String errorMessage) {
        moviesLiveData.postValue(Resource.error(errorMessage, movies));
    }

    private void fetchFromDb(final int pageNumber) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Movie> dbMovies = movieDao.getMoviesSync();
                if (dbMovies != null && (dbMovies.size() / MOVIES_IN_PAGE) >= pageNumber) {
                    movies = dbMovies;
                    updateSuccessMoviesToLiveData();
                } else {
                    fetchFromWs(pageNumber);
                }
            }
        }).start();
    }

    private void fetchFromWs(int pageId) {
        webRequests.getMovies(pageId, MyMovieApplication.getInstance().getString(R.string.api_key)).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();

                    if (movieResponse == null) {
                        updateErrorMoviesToLiveData(EMPTY_BODY_FROM_WS);
                        return;
                    }

                    List<Movie> newMovies = movieResponse.getResults();
                    movies.addAll(newMovies);
                    updateSuccessMoviesToLiveData();
                    saveAllMoviesInDatabase(newMovies);
                } else {
                    String responseErrorMessage = UNKNOWN_ERROR;

                    try {
                        if (response.errorBody() != null) {
                            responseErrorMessage = response.errorBody().string();
                        }
                    } catch (Exception exception) {
                        responseErrorMessage += response.code();
                    }

                    updateErrorMoviesToLiveData(responseErrorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                updateErrorMoviesToLiveData(t.getMessage());
            }
        });
    }

    private static class saveAllMoviesAsyncTask extends AsyncTask<List<Movie>, Void, Void> {
        private MovieDao movieDao;

        private saveAllMoviesAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Movie>... lists) {
            movieDao.insertNewMovies(lists[0]);
            return null;
        }
    }

    private static class DeleteAllMovieAsyncTask extends AsyncTask<Void, Void, Void> {

        private MovieDao movieDao;

        private DeleteAllMovieAsyncTask(MovieDao movieDao) {
            this.movieDao = movieDao;
        }

        @Override
        protected Void doInBackground(Void... movies) {
            movieDao.deleteAllMovies();
            return null;
        }
    }

}
