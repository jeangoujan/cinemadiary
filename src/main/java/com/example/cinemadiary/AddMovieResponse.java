package com.example.cinemadiary;

public class AddMovieResponse {

    private Long id;
    private String movieName;

    public AddMovieResponse(){

    }

    public AddMovieResponse(Long id, String movieName) {
        this.id = id;
        this.movieName = movieName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }}
