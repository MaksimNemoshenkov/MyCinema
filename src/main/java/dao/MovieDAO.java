package dao;


import models.Movie;

import java.util.List;

public interface MovieDAO {
    Movie findById (int id);
    void save (Movie movie);
    void update (Movie movie);
    void delete (Movie movie);
    List<Movie> findAll();
}
