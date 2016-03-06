package com.example.admin.moviesapp.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.moviesapp.DetailFragment;
import com.example.admin.moviesapp.R;

/**
 * Created by Admin on 20-02-2016.
 */
public class VideoAdapter extends CursorAdapter {
    private static final String LOG_TAG = VideoAdapter.class.getSimpleName();

    public VideoAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.trailer_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();

        // Read video information from cursor
        viewHolder.textName.setText(cursor.getString(DetailFragment.COL_VIDEO_NAME));
    }

    // Cache of the children view
    static class ViewHolder {
        TextView textName;
        ImageView imageView;

        public ViewHolder(View view) {
            textName = (TextView) view.findViewById(R.id.textView_name);
            imageView = (ImageView) view.findViewById(R.id.imageView_thumbnail);
        }
    }
}

