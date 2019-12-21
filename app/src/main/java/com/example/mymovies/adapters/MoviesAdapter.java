package com.example.mymovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.mymovies.R;
import com.example.mymovies.models.Movie;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private OnItemClickListener listener;

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final Movie movie = mMovies.get(position);
        Glide
                .with(context)
                .load(movie.getPosterPath())
                .placeholder(R.drawable.ic_launcher_foreground)
                .fitCenter()
                .into(holder.movieImageView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView movieImageView;

        MyViewHolder(View view) {
            super(view);
            movieImageView = view.findViewById(R.id.movie_item_image_view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener == null || position == RecyclerView.NO_POSITION) return;
                    Movie movie = mMovies.get(position);
                    listener.onItemClick(movie);
                }
            });
        }
    }

    public void setMovies(List<Movie> movies) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MovieDiffCallback(mMovies , movies));
        mMovies.clear();
        mMovies.addAll(movies);
        diffResult.dispatchUpdatesTo(this);
    }

    public interface OnItemClickListener{
        void onItemClick(Movie movie);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
