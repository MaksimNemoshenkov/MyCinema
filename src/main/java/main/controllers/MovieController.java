package main.controllers;

import models.Movie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.MovieService;

@Controller
public class MovieController {

    MovieService movieService = new MovieService();

    @GetMapping("/movie")
    public String movie(Model model){
        Iterable<Movie> movies = movieService.findAllMovie();
        model.addAttribute("movies", movies);
        return"movie-main";
    }
    @GetMapping("/movie/add")
    public String movieAdd(Model model){
        return"movie-add";
    }
    @PostMapping("/movie/add")
    public String moviePostAdd(@RequestParam String title, @RequestParam int rating, Model model){
        Movie movie = new Movie(title, rating);
        movieService.saveMovie(movie);
        return"redirect:/movie";
    }


}
