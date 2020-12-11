package com.cinema.services;

import com.cinema.models.Movie;
import com.cinema.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieService {
    private final MovieRepository movieRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }
    public Movie getOne(long id){
        return movieRepository.getOne(id);
    }
    public Movie save(Movie movie){
       return movieRepository.save(movie);
    }
    public Movie save(String name, int rating){
        Movie movie = new Movie(name, rating);
        return movieRepository.save(movie);
    }
    public Movie update(long id, String name, int rating){
        Movie movie = movieRepository.getOne(id);
        movie.setName(name);
        movie.setRating(rating);
        return movieRepository.save(movie);
    }
    public Movie update(Movie movieFromDb, Movie movie){
        movieFromDb.setName(movie.getName());
        movieFromDb.setRating(movie.getRating());
        return movieRepository.save(movieFromDb);
    }
    public void delete(Movie movie){
        movieRepository.delete(movie);
    }
    public void deleteById(long id){
        movieRepository.deleteById(id);
    }
    public Boolean existsById(long id){
        return movieRepository.existsById(id);
    }
}
