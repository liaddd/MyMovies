package com.example.mymovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie implements Parcelable {

    private static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/w342";

    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @PrimaryKey
    @SerializedName("id")
    private Integer id;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("title")
    private String title;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("vote_count")
    private Integer voteCount;
    @SerializedName("video")
    private Boolean video;
    @SerializedName("vote_average")
    private Double voteAverage;

    public Movie(String posterPath, boolean adult, String overview, String releaseDate, Integer id,
                 String originalTitle, String originalLanguage, String title, String backdropPath, Double popularity,
                 Integer voteCount, Boolean video, Double voteAverage) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        if (posterPath != null && !posterPath.contains(IMAGE_URL_BASE_PATH))
            posterPath = IMAGE_URL_BASE_PATH + posterPath;
        return posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Integer getId() {
        return id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeValue(this.id);
        dest.writeString(this.originalTitle);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.title);
        dest.writeString(this.backdropPath);
        dest.writeValue(this.popularity);
        dest.writeValue(this.voteCount);
        dest.writeValue(this.video);
        dest.writeValue(this.voteAverage);
    }

    protected Movie(Parcel in) {
        this.posterPath = in.readString();
        this.adult = in.readByte() != 0;
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.originalTitle = in.readString();
        this.originalLanguage = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.voteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return adult == movie.adult &&
                Objects.equals(posterPath, movie.posterPath) &&
                Objects.equals(overview, movie.overview) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(id, movie.id) &&
                Objects.equals(originalTitle, movie.originalTitle) &&
                Objects.equals(originalLanguage, movie.originalLanguage) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(backdropPath, movie.backdropPath) &&
                Objects.equals(popularity, movie.popularity) &&
                Objects.equals(voteCount, movie.voteCount) &&
                Objects.equals(video, movie.video) &&
                Objects.equals(voteAverage, movie.voteAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posterPath, adult, overview, releaseDate, id, originalTitle, originalLanguage, title, backdropPath, popularity, voteCount, video, voteAverage);
    }
}
