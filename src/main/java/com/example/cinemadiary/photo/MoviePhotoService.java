package com.example.cinemadiary.photo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.example.cinemadiary.MovieEntry;
import com.example.cinemadiary.MovieEntryRepository;

@Service
public class MoviePhotoService {
    private final MoviePhotoRepository moviePhotoRepository;
    private final MovieEntryRepository movieEntryRepository;

    public MoviePhotoService(
        MoviePhotoRepository moviePhotoRepository,
        MovieEntryRepository movieEntryRepository
    ){
        this.moviePhotoRepository = moviePhotoRepository;
        this.movieEntryRepository = movieEntryRepository;
    }
    
    public MoviePhotoResponse uploadPhoto(Long movieId, MultipartFile file){
        MovieEntry movieEntry = movieEntryRepository.findById(movieId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found"));

        long photosCount = moviePhotoRepository.countByMovieEntryId(movieId);

        if (photosCount >= 3){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Movie already has maximum number of photos"
            );
        }

        if (file.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "File is empty"
            );
        }

        long maxFileSize = 10 * 1024 * 1024;
        
        if(file.getSize() > maxFileSize){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "File size must be less than 10 MB"
            );
        }
        
        
        Set<String> allowedContentTypes = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
        );

        String contentType = file.getContentType();

        if(contentType == null || !allowedContentTypes.contains(contentType)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Only JPEG, PNG, WEBP images are allowed"
            );
        }

        Path uploadDir = Paths.get("uploads/movies"); 

        try {
            Files.createDirectories(uploadDir);
        } catch (IOException exception){ 
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Could not create upload directory"
            );
        }

        String originalFileName = file.getOriginalFilename(); 
        String fileName = UUID.randomUUID() + "-" + originalFileName; 

        Path filePath = uploadDir.resolve(fileName); 

        try {
            file.transferTo(filePath);
        } catch (IOException exception){
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Could not save file"
            );
        }

        MoviePhoto moviePhoto = new MoviePhoto();
        
        moviePhoto.setMovieEntry(movieEntry);
        moviePhoto.setFileName(fileName);
        moviePhoto.setFilePath(filePath.toString());
        moviePhoto.setContentType(contentType);
        moviePhoto.setFileSize(file.getSize());
        moviePhoto.setCreatedAt(LocalDateTime.now());

        MoviePhoto savedPhoto = moviePhotoRepository.save(moviePhoto);
        

        return new MoviePhotoResponse(
            savedPhoto.getId(),
            savedPhoto.getFileName(),
            "/" + savedPhoto.getFilePath(),
            savedPhoto.getContentType(),
            savedPhoto.getFileSize()
        );
        }


    public List<MoviePhotoResponse> getPhotosByMovieId(Long movieId){
        if (!movieEntryRepository.existsById(movieId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
        return moviePhotoRepository.findByMovieEntryId(movieId)
            .stream()
            .map(moviePhoto -> toListResponse(moviePhoto))
            .toList();
    }


    public void deletePhoto(Long movieId, Long photoId){
        if(!movieEntryRepository.existsById(movieId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }

        MoviePhoto moviePhoto = moviePhotoRepository.findById(photoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found"));

        if (!moviePhoto.getMovieEntry().getId().equals(movieId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found for this movie");
        }

        Path filePath = Paths.get(moviePhoto.getFilePath());

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException exception){
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Could not delete file"
            );
        }
        moviePhotoRepository.delete(moviePhoto);
    }


    private MoviePhotoResponse toListResponse(MoviePhoto moviePhoto){
        return new MoviePhotoResponse(
            moviePhoto.getId(),
            moviePhoto.getFileName(),
            "/" + moviePhoto.getFilePath(),
            moviePhoto.getContentType(),
            moviePhoto.getFileSize()
        );
    }

    

    }


