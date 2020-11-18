package services;

import dao.MovieDAO;
import dao.MovieDAOImpl;
import models.Movie;

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
