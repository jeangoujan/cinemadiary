package com.example.cinemadiary.photo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePhotoRepository extends JpaRepository<MoviePhoto, Long> {

    long countByMovieEntryId(Long movieEntryId);
}

