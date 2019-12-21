package com.example.mymovies.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mymovies.R;
import com.example.mymovies.activities.MainActivity;
import com.example.mymovies.models.Movie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MovieDetailsFragment extends Fragment {

    private ImageView movieImageView , likeImageView;
    private TextView titleTextView, overviewTextView, releaseDateTextView, rateTextView;
    private static final String MOVIE_DATA = "movie_data";


    /**
     * @return A new instance of fragment MovieDetailsFragment.
     */

    public static MovieDetailsFragment newInstance(@NonNull Movie movie) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(MOVIE_DATA, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        Movie movie;
        if (bundle != null) {
            movie = bundle.getParcelable(MOVIE_DATA);
            if (movie != null && getContext() != null) {
                titleTextView.setText(movie.getTitle());
                Glide.with(getContext()).load(movie.getPosterPath()).into(movieImageView);
                if (movie.getOverview() != null) overviewTextView.setText(movie.getOverview());
                String releaseDate = movie.getReleaseDate().split("-")[0];
                if (releaseDate != null) releaseDateTextView.setText(releaseDate);
                if (movie.getVoteAverage() != null) {
                    int averageVote = (int) Math.round(movie.getVoteAverage());
                    rateTextView.setText(averageVote + getString(R.string.movie_max_average_vote));
                }
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MainActivity context = (MainActivity) getContext();
        if (context != null) {
            if (!context.isTwoPane()) context.showActionBarBackButton(true);
        }
        return inflater.inflate(R.layout.movie_details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewAndListeners(view);
    }

    private void initViewAndListeners(final View view) {
        likeImageView = view.findViewById(R.id.movie_details_fragment_like_image_view);
        movieImageView = view.findViewById(R.id.movie_details_fragment_image_view);
        overviewTextView = view.findViewById(R.id.movie_details_fragment_desc_text_view);
        releaseDateTextView = view.findViewById(R.id.movie_details_fragment_release_date_text_view);
        rateTextView = view.findViewById(R.id.movie_details_fragment_rate_text_view);
        titleTextView = view.findViewById(R.id.movie_details_fragment_title_text_view);
        view.findViewById(R.id.movie_details_fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeImageView.setVisibility(View.VISIBLE);
                Animation transAnimation = AnimationUtils.loadAnimation(view.getContext(), R.anim.translate_animation);
                likeImageView.startAnimation(transAnimation);
            }
        });
    }
}
