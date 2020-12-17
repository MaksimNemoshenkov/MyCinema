package com.cinema.controllers.rest;

import com.cinema.models.Movie;
import com.cinema.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mov")
public class MovieRestController {

    private final MovieService movieService;

    @Autowired
    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> list(){
        return movieService.findAll();
    }

    @GetMapping("{id}")
    public Movie getOne(@PathVariable("id") Movie movie){
        return movie;
    }

    @PostMapping
    public Movie create(@RequestBody Movie movie){
        return movieService.save(movie);
    }

    @PutMapping("{id}")
    public Movie update (@PathVariable("id") Movie movieFromDB,@RequestBody Movie movie){
        return movieService.update(movieFromDB, movie);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Movie movie){
        movieService.delete(movie);
    }
}