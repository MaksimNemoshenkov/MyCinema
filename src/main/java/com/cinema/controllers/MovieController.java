package com.cinema.controllers;

import com.cinema.models.Movie;
import com.cinema.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/movie")
    public String movie(Model model){
        Iterable<Movie> movies = movieRepository.findAll();
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
        movieRepository.save(movie);
        return"redirect:/movie";
    }
    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable(value = "id") int id, Model model){
        if(!movieRepository.existsById(id)){
            return "redirect:/movie";
        }
        model.addAttribute("movie", movieRepository.findById(id).orElseThrow());
        return"movie-detail";
    }
    @GetMapping("/movie/{id}/edit")
    public String movieEdit(@PathVariable(value = "id") int id, Model model){
        if(!movieRepository.existsById(id)){
            return "redirect:/movie";
        }
        model.addAttribute("movie", movieRepository.findById(id).orElseThrow());
        return"movie-edit";
    }
    @PostMapping("/movie/{id}/edit")
    public String cinemaMovieUpdate(@PathVariable(value = "id") int id, @RequestParam String name, @RequestParam int rating, Model model){
        Movie movie = movieRepository.findById(id).orElseThrow();
        movie.setName(name);
        movie.setRating(rating);
        movieRepository.save(movie);
        return"redirect:/movie";
    }
    @PostMapping("/movie/{id}/remove")
    public String cinemaMovieDelete(@PathVariable(value = "id") int id, Model model){
        Movie movie = movieRepository.findById(id).orElseThrow();
        movieRepository.delete(movie);
        return"redirect:/movie";
    }
}
