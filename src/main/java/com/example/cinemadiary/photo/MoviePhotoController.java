package com.example.cinemadiary.photo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class MoviePhotoController {
    private static final Logger log = LoggerFactory.getLogger(MoviePhotoController.class);

    private final MoviePhotoService moviePhotoService;

    public MoviePhotoController(MoviePhotoService moviePhotoService){
        this.moviePhotoService = moviePhotoService;
    }

    @PostMapping("/api/movies/{movieId}/photos")
    @ResponseStatus(HttpStatus.CREATED)
    public MoviePhotoResponse uploadPhoto(@PathVariable Long movieId, @RequestParam("file") MultipartFile file){
        log.info("uploadPhoto was called for movieId: {}", movieId);
        return moviePhotoService.uploadPhoto(movieId, file);
    }


    @GetMapping("/api/movies/{movieId}/photos")
    @ResponseStatus(HttpStatus.OK)
    public List<MoviePhotoResponse> getPhotosByMovieId(@PathVariable Long movieId){
        log.info("getPhotosByMovieId was called for movieId: {}", movieId);
        return moviePhotoService.getPhotosByMovieId(movieId);
    }
    
}
