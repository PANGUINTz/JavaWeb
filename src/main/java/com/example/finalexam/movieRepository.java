package com.example.finalexam;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface movieRepository extends MongoRepository<Movie, String> {
    List<Movie> findMovieByTitleIsContainingIgnoreCase(String title);
}
