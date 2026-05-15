package com.example.cinemadiary;

import java.time.LocalDate;

public class MovieEntryDetailsResponse {
    private Long id;
    private String movieName;
    private LocalDate watchDate;
    private Integer generalRating;
    private Integer plotRating;
    private Integer actingRating;
    private Integer atmosphereRating;
    private Integer soundtrackRating;
    private Integer emotionalRating;
    private String review;

    public MovieEntryDetailsResponse(){

    }

    public MovieEntryDetailsResponse(Long id, String movieName, LocalDate watchDate, Integer generalRating, Integer plotRating, Integer actingRating, Integer atmosphereRating, Integer soundtrackRating, Integer emotionalRating, String review){
        this.id = id;
        this.movieName = movieName;
        this.watchDate = watchDate;
        this.generalRating = generalRating;
        this.plotRating = plotRating;
        this.actingRating = actingRating;
        this.atmosphereRating = atmosphereRating;
        this.soundtrackRating = soundtrackRating;
        this.emotionalRating = emotionalRating;
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

    public Integer getPlotRating() {
        return plotRating;
    }

    public void setPlotRating(Integer plotRating) {
        this.plotRating = plotRating;
    }

    public Integer getActingRating() {
        return actingRating;
    }

    public void setActingRating(Integer actingRating) {
        this.actingRating = actingRating;
    }

    public Integer getAtmosphereRating() {
        return atmosphereRating;
    }

    public void setAtmosphereRating(Integer atmosphereRating) {
        this.atmosphereRating = atmosphereRating;
    }

    public Integer getSoundtrackRating() {
        return soundtrackRating;
    }

    public void setSoundtrackRating(Integer soundtrackRating) {
        this.soundtrackRating = soundtrackRating;
    }

    public Integer getEmotionalRating() {
        return emotionalRating;
    }

    public void setEmotionalRating(Integer emotionalRating) {
        this.emotionalRating = emotionalRating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    
}
