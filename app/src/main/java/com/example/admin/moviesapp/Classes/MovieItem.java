package com.example.admin.moviesapp.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Admin on 02-02-2016.
 */

/* data movie item
"id":135397,
"original_title":
"Jurassic World"
,"original_language":
"en","title":"Jurassic World"
,"backdrop_path":"\/dkMD5qlogeRMiEixC4YNPUvax2T.jpg"
,"popularity":28.942629,"vote_count":3898,
"video":false,
"vote_average":6.71}
 */
public class MovieItem implements Parcelable {
    private int id;
    private String title;
    private String originalTitle;
    private String posterPath;
    private String backdropPath;
    private String overview;
    private Date releaseDate;
    private Double voteAverage;
    private Double popularity;
    private int voteCount;
    private boolean adult = false;
    private boolean video = false;

    public MovieItem()
    {

    }
    public int getId()
    {
        return  id;
    }

    public void setId(int id)
    {
        this.id = id;

    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getOriginalTitle()
    {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle)
    {
        this.originalTitle = originalTitle;
    }

    public String getOverview()
    {
        return overview;
    }

    public void setOverview(String overview)
    {
        this.overview = overview;
    }

    public String getPosterPath()
    {
        return posterPath;
    }

    public void setPosterPath(String posterPath)
    {
        this.posterPath =posterPath;
    }

    public String getBackdropPath()
    {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath)
    {
        this.backdropPath = backdropPath;
    }

    public Date getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public double getPopularity()
    {
        return popularity;
    }

    public void setPopularity(Double popularity)
    {
        this.popularity = popularity;
    }

    public String getRating()
    {
        return voteAverage + "/" + voteCount;
    }

    public boolean isAdult()
    {
        return adult;
    }

    public void setAdult(Boolean adult)
    {
        this.adult = adult;
    }

    public boolean isVideo()
    {
        return video;
    }
    public void setVoteAverage(Double voteAverage)
    {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage()
    {
        return voteAverage;
    }

    public void setVideo(boolean video)
    {
        this.video = video;
    }

    public  int getVoteCount()
    {
        return voteCount;
    }

    public void setVoteCount(int voteCount)
    {
        this.voteCount = voteCount;
    }

    public String toString ()
    {
        return title + " (" + id + ")";
    }
public static final Parcelable.Creator<MovieItem> CREATOR
        = new Parcelable.Creator<MovieItem>()
{


    @Override
    public MovieItem createFromParcel( Parcel in ) {
        return  new MovieItem(in);
    }

    @Override
public MovieItem[] newArray(int size)
    {
        return new MovieItem[size];
    }
};

            @Override
    public int describeContents() {
                return 0;
            }

    @Override
    public void writeToParcel(Parcel out, int flags)
    {
        out.writeInt(id);
        out.writeString(title);
        out.writeString(originalTitle);
        out.writeString(overview);
        out.writeString(posterPath);
        out.writeString(backdropPath);
        out.writeLong(releaseDate.getTime());
        out.writeDouble(popularity);
        out.writeDouble(voteAverage);
        out.writeInt(voteCount);
        out.writeString(Boolean.toString(adult));
        out.writeString(Boolean.toString(video));

    }

    private MovieItem (Parcel in )
    {
        id = in.readInt();
        title = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        backdropPath = in.readString();
        posterPath = in.readString();
        voteCount = in.readInt();
        adult = Boolean.parseBoolean(in.readString());
        video = Boolean.parseBoolean(in.readString());
        releaseDate = new Date(in.readLong());



    }

}