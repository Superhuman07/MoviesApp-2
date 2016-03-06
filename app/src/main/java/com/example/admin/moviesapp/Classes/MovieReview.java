package com.example.admin.moviesapp.Classes;

/**
 * Created by Admin on 20-02-2016.
 */
public class MovieReview {
    private String id;
    private String author;
    private String content;
     private  String url;

    public MovieReview(){

    }


    public String getid()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id =id;
    }
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }


    public  String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

}
