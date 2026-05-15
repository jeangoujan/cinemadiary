package com.example.cinemadiary;

import java.time.LocalDate;

public class MovieEntryResponse {
    private Long id;
    private String movieName;
    private LocalDate watchDate;
    private Integer generalRating;
    private String review;

    public MovieEntryResponse(){

    }

    public MovieEntryResponse(Long id, String movieName, LocalDate watchDate, Integer generalRating, String review){
        this.id = id;
        this.movieName = movieName;
        this.watchDate = watchDate;
        this.generalRating = generalRating;
        this.review = review;
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
    }

    public LocalDate getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(LocalDate watchDate) {
        this.watchDate = watchDate;
    }

    public Integer getGeneralRating() {
        return generalRating;
    }

    public void setGeneralRating(Integer generalRating) {
        this.generalRating = generalRating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    
}


