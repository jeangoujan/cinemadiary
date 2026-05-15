package com.example.cinemadiary;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieEntryRepository extends JpaRepository<MovieEntry, Long> {
}
