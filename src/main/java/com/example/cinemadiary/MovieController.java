package com.example.cinemadiary;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger; // Логи
import org.slf4j.LoggerFactory; // Логи
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

    private final MovieEntryService movieEntryService;


    public MovieController(MovieEntryService movieEntryService) {
        this.movieEntryService = movieEntryService;
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddMovieResponse addMovie(@Valid @RequestBody AddMovieRequest request) {
        log.info("addMovie called");

        return movieEntryService.addMovie(request);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MovieEntryResponse> getAllMovies() {
        log.info("getAllMovies called");
        return movieEntryService.getAllMovies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieEntryDetailsResponse getMovieById(@PathVariable Long id){
        log.info("getMovieById was called with id: ", id);
        return movieEntryService.getMovieById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovieById(@PathVariable Long id){
        log.info("deleteMovieById was called with id: ", id);
        movieEntryService.deleteMovieById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieEntryDetailsResponse updateMovieById(@PathVariable Long id, @Valid @RequestBody UpdateMovieRequest request){
        log.info("updateMovieById was called with id: {}", id);
        return movieEntryService.updateMovieById(id, request);
    }
}
