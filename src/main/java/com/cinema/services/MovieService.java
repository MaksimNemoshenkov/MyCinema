package com.cinema.services;

import com.cinema.models.Movie;

import java.util.List;

public interface MovieService {
        Movie getOne(Long id);
        Movie save(Movie Movie);
        Movie save(String name, Integer rating);
        List<Movie> findAll();
        Movie update(Movie movieFromDb, Movie movie);
        Movie update(Long id, String name, Integer rating);
        void deleteById(Long id);
        void delete(Movie movie);
        Boolean existsById(Long id);
}
