package io.javabrains.moviecatalogservice.models;

public class Movie {

    private String movieId;
    private String title;
    private String overview;

    //this default constructor is needed when you are mapping
    public Movie() {

    }
    public Movie(String movieId, String name, String overview) {
        this.movieId = movieId;
        this.title = name;
        this.overview = overview;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
