package com.example.admin.moviesapp;

/**
 * Created by Admin on 02-02-2016.
 */
public class MovieItem {
    String id;
    String title;
    String posterPath;
    String overview;
    String releaseDate;
    Double voteAverage;
    Double popularity;

    public MovieItem(String movieId,  String movieTitle, String moviePosterPath, String movieOverview, String movieReleaseDate, Double movieVoteAverage, Double moviePopularity) {
        id = movieId;
        title = movieTitle;
        posterPath = moviePosterPath;
        overview = movieOverview;
        releaseDate = movieReleaseDate;
        voteAverage = movieVoteAverage;
        popularity = moviePopularity;
    }
}