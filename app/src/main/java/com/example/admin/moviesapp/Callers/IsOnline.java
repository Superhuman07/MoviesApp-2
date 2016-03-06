package com.example.admin.moviesapp.Callers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Admin on 05-02-2016.
 */
public class IsOnline {
    public static boolean isNetworkStatusAvailable (Context context) {
        ConnectivityManager connectiveManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectiveManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
