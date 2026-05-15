package com.example.cinemadiary;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovieEntryService {

    private final MovieEntryRepository movieEntryRepository;

    public MovieEntryService(MovieEntryRepository movieEntryRepository){
        this.movieEntryRepository = movieEntryRepository;
    }

    public AddMovieResponse addMovie(AddMovieRequest request){
        MovieEntry movieEntry = new MovieEntry();
        movieEntry.setMovieName(request.getMovieName());
        movieEntry.setWatchDate(request.getWatchDate());
        movieEntry.setGeneralRating(request.getGeneralRating());
        movieEntry.setPlotRating(request.getPlotRating());
        movieEntry.setActingRating(request.getActingRating());
        movieEntry.setAtmosphereRating(request.getAtmosphereRating());
        movieEntry.setSoundtrackRating(request.getSoundtrackRating());
        movieEntry.setEmotionalRating(request.getEmotionalRating());
        movieEntry.setReview(request.getReview());

        LocalDateTime now = LocalDateTime.now();
        movieEntry.setCreatedAt(now);
        movieEntry.setUpdatedAt(now);

        MovieEntry savedMovieEntry = movieEntryRepository.save(movieEntry);

        return new AddMovieResponse(savedMovieEntry.getId(), savedMovieEntry.getMovieName());
    }

    public List<MovieEntryResponse> getAllMovies(){
        return movieEntryRepository.findAll()
        .stream()
        .map(
            movieEntry -> new MovieEntryResponse(
                movieEntry.getId(),
                movieEntry.getMovieName(),
                movieEntry.getWatchDate(),
                movieEntry.getGeneralRating(),
                movieEntry.getReview()
            )
        ).toList();
    }

    public MovieEntryDetailsResponse getMovieById(Long id){
        MovieEntry movieEntry = movieEntryRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Element not found"));

            return new MovieEntryDetailsResponse(
            movieEntry.getId(),
            movieEntry.getMovieName(),
            movieEntry.getWatchDate(),
            movieEntry.getGeneralRating(),
            movieEntry.getPlotRating(),
            movieEntry.getActingRating(),
            movieEntry.getAtmosphereRating(),
            movieEntry.getSoundtrackRating(),
            movieEntry.getEmotionalRating(),
            movieEntry.getReview()
    );
    }
}
