package com.cinema.controllers;

import com.cinema.models.Movie;
import com.cinema.repo.MovieRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mov")
public class MovieRestController {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieRestController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> list(){
        return movieRepository.findAll();
    }

    @GetMapping("{id}")
    public Movie getOne(@PathVariable("id") Movie movie){
        return movie;
    }

    @PostMapping
    public Movie create(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }

    @PutMapping("{id}")
    public Movie update (
            @PathVariable("id") Movie movieFromDB,
            @RequestBody Movie movie
    ){
      //  BeanUtils.copyProperties(movie, movieFromDB, "id");
        movieFromDB.setName(movie.getName());
        movieFromDB.setRating(movie.getRating());
        return movieRepository.save(movieFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Movie movie){
        movieRepository.delete(movie);
    }
}