package com.cinema.services;

import com.cinema.exceptions.NoDataFoundException;
import com.cinema.exceptions.NotFoundException;
import com.cinema.models.Movie;
import com.cinema.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieRepository movieRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public List<Movie> findAll(){
        List<Movie> movies = movieRepository.findAll();
        if(movies.isEmpty()){
            throw new NoDataFoundException("movies");
        }
        return movieRepository.findAll();
    }
    @Override
    public Movie getOne(Long id){
        existsById(id);
        return movieRepository.getOne(id);
    }
    @Override
    public Movie save(Movie movie){
       return movieRepository.save(movie);
    }
    @Override
    public Movie save(String name, Integer rating){
        Movie movie = new Movie(name, rating);
        return movieRepository.save(movie);
    }
    @Override
    public Movie update(Long id, String name, Integer rating){
        Movie movie = getOne(id);
        movie.setName(name);
        movie.setRating(rating);
        return movieRepository.save(movie);
    }
    @Override
    public Movie update(Movie movieFromDb, Movie movie){
        movieFromDb.setName(movie.getName());
        movieFromDb.setRating(movie.getRating());
        return movieRepository.save(movieFromDb);
    }
    @Override
    public void delete(Movie movie){
        movieRepository.delete(movie);
    }
    @Override
    public void deleteById(Long id){
        existsById(id);
        movieRepository.deleteById(id);
    }
    @Override
    public Boolean existsById(Long id){
        if(!movieRepository.existsById(id)){
            throw new NotFoundException("movie", id);
        }
        return true;
    }
}
