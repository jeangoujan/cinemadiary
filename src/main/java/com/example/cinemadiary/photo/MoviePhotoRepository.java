package com.example.cinemadiary.photo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePhotoRepository extends JpaRepository<MoviePhoto, Long> {

    long countByMovieEntryId(Long movieEntryId);

    List<MoviePhoto> findByMovieEntryId(Long movieEntryId);
}


