package com.example.mymovies.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymovies.R;
import com.example.mymovies.activities.MainActivity;
import com.example.mymovies.adapters.MoviesAdapter;
import com.example.mymovies.models.Movie;
import com.example.mymovies.utils.Resource;
import com.example.mymovies.viewmodel.MoviesFragmentViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesFragment extends Fragment {

    private MoviesAdapter adapter;
    private RecyclerView mRecyclerView;
    private MoviesFragmentViewModel viewModel;

    /**
     * @return A new instance of fragment MoviesFragment.
     */

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(MoviesFragmentViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        setScrollListener();
        setItemClickListener();
        observeMovies();
    }

    private void observeMovies() {
        viewModel.getAllMovies().observe(getViewLifecycleOwner(), new Observer<Resource<List<Movie>>>() {
            @Override
            public void onChanged(Resource<List<Movie>> movies) {
                if (movies == null) return;
                handleMoviesUpdate(movies);
            }
        });
    }

    private void handleMoviesUpdate(Resource<List<Movie>> movies) {
        switch (movies.status) {
            case SUCCESS:
                if (movies.data != null && movies.data.size() > 0) {
                    updateMovies(movies.data);
                }
                break;
            case ERROR:
                showError(movies.message);
                break;
        }
    }

    private void showError(String message) {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateMovies(List<Movie> movies) {
        if (adapter != null) {
            adapter.setMovies(movies);
        }
    }

    private void initViews(View view) {
        Context context = getContext();
        if (context == null) return;
        ((MainActivity) context).showActionBarBackButton(false);
        initMoviesRecyclerView(view, context);
    }

    private void initMoviesRecyclerView(View view, Context context) {
        mRecyclerView = view.findViewById(R.id.fragment_movies_recycler_view);
        if (adapter == null) {
            adapter = new MoviesAdapter(context);
        }
        GridLayoutManager mLayoutManager = new GridLayoutManager(context, ((MainActivity) context).getScreenWidth(), RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    private void setItemClickListener() {

        adapter.setOnItemClickListener(new MoviesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                if (movie != null) {
                    MainActivity context = (MainActivity) getContext();
                    if (context != null) {
                        context.replaceFragment(MovieDetailsFragment.newInstance(movie), true, context.isTwoPane());
                    }
                }
            }
        });
    }

    private void setScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

                /* end of scrolling - fetching more data from db/web */
                if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() >= adapter.getItemCount() - 4) {
                    viewModel.getNextPage();
                }
            }
        });
    }

}

