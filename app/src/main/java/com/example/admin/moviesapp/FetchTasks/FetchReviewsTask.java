package com.example.admin.moviesapp.FetchTasks;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.admin.moviesapp.Callers.Downloaders;
import com.example.admin.moviesapp.Classes.MovieReview;
import com.example.admin.moviesapp.data.MovieContract;

/**
 * Created by Admin on 22-02-2016.
 */
public class FetchReviewsTask extends AsyncTask<String, Void, MovieReview[]> {
    private static final String LOG_TAG = FetchReviewsTask.class.getSimpleName();

    private Context mcontext;
    private long mMovieId;
    private Callback mcallback;

    public interface Callback
    {
        void onFetchReviewFinished();
    }

    public FetchReviewsTask(Context context, Callback callback)
    {
        this.mcontext = context;
        this.mcallback = callback;
    }

    @Override
    protected MovieReview[] doInBackground(String... params)
    {
        if (params.length == 0)
        {
            return  null;

        }

        mMovieId = Long.parseLong(params[0]);
        String api_key = params[1].toString();

        if (api_key == null)
        {
            Log.d(LOG_TAG, "no apik key");
            return null;
        }
        if (mMovieId == 0)
        {
            Log.d(LOG_TAG, "no moviesid");
            return null;
        }

        // downloading movies reviews !
        String reviewJsonStr = Downloaders.downloadMovieReviews(api_key, mMovieId);

        if (reviewJsonStr == null)
        {
            Log.d(LOG_TAG, "Movies reviews are null!");
            return null;
        }

        return Downloaders.getReviewDataFromJson(reviewJsonStr);
    }
    @Override
    protected void onPostExecute(MovieReview[] movieReviews)
    {
        super.onPostExecute(movieReviews);

        if(movieReviews != null)
        {
            for (MovieReview movieReview: movieReviews)
            {
                ContentValues reviewValues = new ContentValues();
               reviewValues.put(MovieContract.ReviewEntry.COLUMN_MOVIE_KEY, mMovieId );
               reviewValues.put(MovieContract.ReviewEntry.COLUMN_ID, movieReview.getid());
                reviewValues.put(MovieContract.ReviewEntry.COLUMN_AUTHOR, movieReview.getAuthor());
                reviewValues.put(MovieContract.ReviewEntry.COLUMN_CONTENT, movieReview.getContent());
                reviewValues.put(MovieContract.ReviewEntry.COLUMN_URL, movieReview.getUrl());

                int updated = mcontext.getContentResolver().update(MovieContract.ReviewEntry.CONTENT_URI, reviewValues, MovieContract.ReviewEntry.COLUMN_MOVIE_KEY + "=? AND "+ MovieContract.ReviewEntry.COLUMN_ID + "=?",
                        new String[] {Long.toString(mMovieId), movieReview.getid()});

                if (updated == 0)
                {
                    mcontext.getContentResolver().insert(MovieContract
                    .ReviewEntry.CONTENT_URI,reviewValues);
                }
            }
        }
        else
        {
            Log.d(LOG_TAG, "moviereviews are null!");
        }

        mcallback.onFetchReviewFinished();
    }


}
