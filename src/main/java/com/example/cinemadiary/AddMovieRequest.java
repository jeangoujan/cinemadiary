package com.example.cinemadiary;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class AddMovieRequest {
    @NotBlank(message = "Movie name is required.")
    @Size(min = 1, max = 255, message = "Movie name bust be between 1 and 255 characters.")
    @NotNull
    private String movieName;

    @NotNull(message = "Watch date is required.")
    @PastOrPresent(message = "WatchDate can't be in Future.")
    private LocalDate watchDate;

    @NotNull(message = "General rating is required.")
    @Max(value = 10, message = "General rating must be at most 10.")
    @Min(value = 1, message = "General rating must be at least 1.")
    private Integer generalRating;

    @NotNull(message = "Plot rating is required.")
    @Max(value = 10, message = "Plot rating must be at most 10.")
    @Min(value = 1, message = "Plot rating must be at least 1.")
    private Integer plotRating;

    @NotNull(message = "Acting rating is required.")
    @Max(value = 10, message = "Acting rating must be at most 10.")
    @Min(value = 1, message = "Acting rating must be at least 1.")
    private Integer actingRating;

    @NotNull(message = "Atmosphere rating is required.")
    @Max(value = 10, message = "Atmosphere rating must be at most 10.")
    @Min(value = 1, message = "Atmosphere rating must be at least 1.")
    private Integer atmosphereRating;

    @NotNull(message = "Soundtrack rating is required.")
    @Max(value = 10, message = "Soundtrack rating must be at most 10.")
    @Min(value = 1, message = "Soundtrack rating must be at least 1.")
    private Integer soundtrackRating;

    @NotNull(message = "Emotional rating is required.")
    @Max(value = 10, message = "Emotional rating must be at most 10.")
    @Min(value = 1, message = "Emotional rating must be at least 1.")
    private Integer emotionalRating;

    @NotBlank(message = "Review is required.")
    @Size(min = 10, max = 512, message = "Review bust be between 5 and 512 characters")
    @NotNull
    private String review;

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
