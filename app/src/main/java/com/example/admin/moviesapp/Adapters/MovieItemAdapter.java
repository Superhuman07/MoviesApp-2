package com.example.admin.moviesapp.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.example.admin.moviesapp.Callers.Downloaders;
import com.example.admin.moviesapp.MainFragment;
import com.example.admin.moviesapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 20-02-2016.
 */
public class MovieItemAdapter extends CursorAdapter {
    private static final String LOG_TAG = MovieItemAdapter.class.getSimpleName();

    /**
     * Cache of the children views for image list item.
     */
    public static class ViewHolder {
        public final ImageView posterView;

        public ViewHolder(View view) {
            posterView = (ImageView) view.findViewById(R.id.list_item_poster_img);
        }
    }

    public MovieItemAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_poster, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        // Read poster path from cursor
        String posterPath = cursor.getString(MainFragment.COL_POSTER_PATH);

        String posterUrlStr = Downloaders.buildPosterImageUrl(posterPath);

        // Using Picasso to fetch images and load them into view
        Picasso.with(context).load(posterUrlStr).into(viewHolder.posterView);
    }
}
