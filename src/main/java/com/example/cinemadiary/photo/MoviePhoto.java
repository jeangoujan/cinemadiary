package com.example.cinemadiary.photo;


import java.time.LocalDateTime;

import com.example.cinemadiary.MovieEntry;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_photos")
public class MoviePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="movie_entry_id", nullable = false)
    private MovieEntry movieEntry;
    private String fileName;
    private String filePath;
    private String contentType;
    private Long fileSize;
    private LocalDateTime createdAt;

    public MoviePhoto(){

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public MovieEntry getMovieEntry() {
        return movieEntry;
    }
    public void setMovieEntry(MovieEntry movieEntry) {
        this.movieEntry = movieEntry;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public Long getFileSize() {
        return fileSize;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
    
}
