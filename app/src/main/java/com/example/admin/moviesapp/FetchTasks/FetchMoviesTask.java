package com.example.admin.moviesapp.FetchTasks;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.admin.moviesapp.Adapters.MovieItemAdapter;
import com.example.admin.moviesapp.Callers.Downloaders;
import com.example.admin.moviesapp.Classes.MovieItem;
import com.example.admin.moviesapp.data.MovieContract;

/**
 * Created by Admin on 20-02-2016.
 */
public class FetchMoviesTask extends AsyncTask<String, Void, MovieItem[]> {
    private static final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    private MovieItemAdapter mMovieAdapter;
    private Context mContext;

    public FetchMoviesTask(Context context, MovieItemAdapter movieAdapter) {
        this.mContext = context;
        this.mMovieAdapter = movieAdapter;
    }

    @Override
    protected MovieItem[] doInBackground(String... params) {
        if (params.length == 0)
            return null;

        String sortBy = params[0];
        String apiKey = params[1];

        if (sortBy == null) {
            Log.d(LOG_TAG, "sortBy is null");
            return null;
        }

        if (apiKey == null) {
            Log.d(LOG_TAG, "apiKey is null");
            return null;
        }

        // download movies data from themoviedb
        String moviesJsonStr = Downloaders.discoverMoviesFromTheMovieDb(apiKey, sortBy);

        if (moviesJsonStr == null) {
            Log.d(LOG_TAG, "moviesJsonStr is null");
            return null;
        }

        // parse the return JSON string to flavor movie object array
        return Downloaders.getMovieDataFromJson(moviesJsonStr);
    }

    @Override
    protected void onPostExecute(MovieItem[] flavorMovies) {
        super.onPostExecute(flavorMovies);

        if (flavorMovies != null) {
            // add or update to database
            for (MovieItem flavorMovie : flavorMovies) {
                ContentValues movieValues = new ContentValues();

                movieValues.put(MovieContract.MovieEntry._ID, flavorMovie.getId());
                movieValues.put(MovieContract.MovieEntry.COLUMN_TITLE, flavorMovie.getTitle());
                movieValues.put(MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE, flavorMovie.getOriginalTitle());
                movieValues.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, flavorMovie.getOverview());
                movieValues.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, flavorMovie.getPosterPath());
                movieValues.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, flavorMovie.getBackdropPath());
                movieValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, flavorMovie.getReleaseDate() == null ? null : flavorMovie.getReleaseDate().getTime());
                movieValues.put(MovieContract.MovieEntry.COLUMN_POPULARITY, flavorMovie.getPopularity());
                movieValues.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE, flavorMovie.getVoteAverage());
                movieValues.put(MovieContract.MovieEntry.COLUMN_VOTE_COUNT, flavorMovie.getVoteCount());
                movieValues.put(MovieContract.MovieEntry.COLUMN_ADULT, flavorMovie.isAdult());
                movieValues.put(MovieContract.MovieEntry.COLUMN_VIDEO, flavorMovie.isVideo());

                int updated = mContext.getContentResolver().update(
                        MovieContract.MovieEntry.CONTENT_URI,
                        movieValues,
                        MovieContract.MovieEntry._ID + "=?",
                        new String[] {Long.toString(flavorMovie.getId())});

                if (updated == 0) {
                    mContext.getContentResolver().insert(
                            MovieContract.MovieEntry.CONTENT_URI,
                            movieValues);
                }
            }
        } else {
            Log.d(LOG_TAG, "flavorMovies is null!");
        }

        // must notify the adapter data changed!!
        mMovieAdapter.notifyDataSetChanged();
    }
}
