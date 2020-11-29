package com.Cinema.services;

import com.Cinema.dao.MovieDAO;
import com.Cinema.dao.MovieDAOImpl;
import com.Cinema.models.Movie;
import com.Cinema.repo.MovieRepository;

import java.util.List;

public class MovieService {
    private MovieDAO movieDAO = new MovieDAOImpl();
    public MovieService(){}

    public Movie findMovie(int id) {
        return movieDAO.findById(id);
    }

    public void saveMovie(Movie movie) {
        movieDAO.save(movie);
    }

    public void deleteMovie(Movie movie) {
        movieDAO.delete(movie);
    }

    public void updateMovie(Movie movie) {
        movieDAO.update(movie);
    }

    public List<Movie> findAllMovie() {
        return movieDAO.findAll();
    }

}
