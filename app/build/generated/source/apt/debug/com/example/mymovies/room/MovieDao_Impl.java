package com.example.mymovies.room;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.mymovies.models.Movie;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MovieDao_Impl implements MovieDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Movie> __insertionAdapterOfMovie;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMovies;

  public MovieDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMovie = new EntityInsertionAdapter<Movie>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `movie_table` (`posterPath`,`adult`,`overview`,`releaseDate`,`id`,`originalTitle`,`originalLanguage`,`title`,`backdropPath`,`popularity`,`voteCount`,`video`,`voteAverage`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Movie value) {
        if (value.getPosterPath() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPosterPath());
        }
        final int _tmp;
        _tmp = value.isAdult() ? 1 : 0;
        stmt.bindLong(2, _tmp);
        if (value.getOverview() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getOverview());
        }
        if (value.getReleaseDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getReleaseDate());
        }
        if (value.getId() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getId());
        }
        if (value.getOriginalTitle() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getOriginalTitle());
        }
        if (value.getOriginalLanguage() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getOriginalLanguage());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getTitle());
        }
        if (value.getBackdropPath() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getBackdropPath());
        }
        if (value.getPopularity() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindDouble(10, value.getPopularity());
        }
        if (value.getVoteCount() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindLong(11, value.getVoteCount());
        }
        final Integer _tmp_1;
        _tmp_1 = value.getVideo() == null ? null : (value.getVideo() ? 1 : 0);
        if (_tmp_1 == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindLong(12, _tmp_1);
        }
        if (value.getVoteAverage() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindDouble(13, value.getVoteAverage());
        }
      }
    };
    this.__preparedStmtOfDeleteAllMovies = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from movie_table";
        return _query;
      }
    };
  }

  @Override
  public void insertNewMovies(final List<Movie> movies) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfMovie.insert(movies);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllMovies() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllMovies.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllMovies.release(_stmt);
    }
  }

  @Override
  public List<Movie> getMoviesSync() {
    final String _sql = "SELECT * FROM movie_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
      final int _cursorIndexOfAdult = CursorUtil.getColumnIndexOrThrow(_cursor, "adult");
      final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
      final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "releaseDate");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfOriginalTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "originalTitle");
      final int _cursorIndexOfOriginalLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "originalLanguage");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdropPath");
      final int _cursorIndexOfPopularity = CursorUtil.getColumnIndexOrThrow(_cursor, "popularity");
      final int _cursorIndexOfVoteCount = CursorUtil.getColumnIndexOrThrow(_cursor, "voteCount");
      final int _cursorIndexOfVideo = CursorUtil.getColumnIndexOrThrow(_cursor, "video");
      final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
      final List<Movie> _result = new ArrayList<Movie>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Movie _item;
        final String _tmpPosterPath;
        _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
        final boolean _tmpAdult;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfAdult);
        _tmpAdult = _tmp != 0;
        final String _tmpOverview;
        _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
        final String _tmpReleaseDate;
        _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
        final Integer _tmpId;
        if (_cursor.isNull(_cursorIndexOfId)) {
          _tmpId = null;
        } else {
          _tmpId = _cursor.getInt(_cursorIndexOfId);
        }
        final String _tmpOriginalTitle;
        _tmpOriginalTitle = _cursor.getString(_cursorIndexOfOriginalTitle);
        final String _tmpOriginalLanguage;
        _tmpOriginalLanguage = _cursor.getString(_cursorIndexOfOriginalLanguage);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpBackdropPath;
        _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
        final Double _tmpPopularity;
        if (_cursor.isNull(_cursorIndexOfPopularity)) {
          _tmpPopularity = null;
        } else {
          _tmpPopularity = _cursor.getDouble(_cursorIndexOfPopularity);
        }
        final Integer _tmpVoteCount;
        if (_cursor.isNull(_cursorIndexOfVoteCount)) {
          _tmpVoteCount = null;
        } else {
          _tmpVoteCount = _cursor.getInt(_cursorIndexOfVoteCount);
        }
        final Boolean _tmpVideo;
        final Integer _tmp_1;
        if (_cursor.isNull(_cursorIndexOfVideo)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getInt(_cursorIndexOfVideo);
        }
        _tmpVideo = _tmp_1 == null ? null : _tmp_1 != 0;
        final Double _tmpVoteAverage;
        if (_cursor.isNull(_cursorIndexOfVoteAverage)) {
          _tmpVoteAverage = null;
        } else {
          _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
        }
        _item = new Movie(_tmpPosterPath,_tmpAdult,_tmpOverview,_tmpReleaseDate,_tmpId,_tmpOriginalTitle,_tmpOriginalLanguage,_tmpTitle,_tmpBackdropPath,_tmpPopularity,_tmpVoteCount,_tmpVideo,_tmpVoteAverage);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
