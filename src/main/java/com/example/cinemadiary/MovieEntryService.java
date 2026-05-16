package com.example.cinemadiary;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;


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
        MovieEntry movieEntry = movieEntryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Element not found"));

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

    public void deleteMovieById(Long id){
       if (!movieEntryRepository.existsById(id)){
       throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"); 
       }
       movieEntryRepository.deleteById(id);
    }

    public MovieEntryDetailsResponse updateMovieById(Long id, UpdateMovieRequest request){
        MovieEntry movieEntry = movieEntryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));
        if (request.getMovieName() != null) {
            movieEntry.setMovieName(request.getMovieName());
        }
        if (request.getWatchDate() != null) {
            movieEntry.setWatchDate(request.getWatchDate());
        }
        if (request.getGeneralRating() != null){
            movieEntry.setGeneralRating(request.getGeneralRating());
        }
        if (request.getPlotRating() != null){
            movieEntry.setPlotRating(request.getPlotRating());
        }
        if (request.getActingRating() != null) {
            movieEntry.setActingRating(request.getActingRating());
        }
        if (request.getAtmosphereRating() != null){
            movieEntry.setAtmosphereRating(request.getAtmosphereRating());
        }
        if (request.getSoundtrackRating() != null) {
            movieEntry.setSoundtrackRating(request.getSoundtrackRating());
        }
        if (request.getEmotionalRating() != null) {
            movieEntry.setEmotionalRating(request.getEmotionalRating());
        }
        if (request.getReview() != null) {
            movieEntry.setReview(request.getReview());
        }

        LocalDateTime now = LocalDateTime.now();

        movieEntry.setUpdatedAt(now);

        MovieEntry savedMovieEntry = movieEntryRepository.save(movieEntry);

        return new MovieEntryDetailsResponse(
            savedMovieEntry.getId(),
            savedMovieEntry.getMovieName(),
            savedMovieEntry.getWatchDate(),
            savedMovieEntry.getGeneralRating(),
            savedMovieEntry.getPlotRating(),
            savedMovieEntry.getActingRating(),
            savedMovieEntry.getAtmosphereRating(),
            savedMovieEntry.getSoundtrackRating(),
            savedMovieEntry.getEmotionalRating(),
            savedMovieEntry.getReview()
        );
    }
}
