package com.cinema.controllers.simple;

import com.cinema.services.MovieService;
import com.cinema.services.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {
    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public String movie(Model model){
        model.addAttribute("movies", movieService.findAll());
        return"movie-main";
    }
    @GetMapping("/movie/add")
    public String movieAdd(Model model){
        return"movie-add";
    }
    @PostMapping("/movie/add")
    public String moviePostAdd(@RequestParam String name, @RequestParam int rating, Model model){
        movieService.save(name, rating);
        return"redirect:/movie";
    }
    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable(value = "id") long id, Model model){
        if(!movieService.existsById(id)){
            return "redirect:/movie";
        }
        model.addAttribute("movie", movieService.getOne(id));
        return"movie-detail";
    }
    @GetMapping("/movie/{id}/edit")
    public String movieEdit(@PathVariable(value = "id") long id, Model model){
        if(!movieService.existsById(id)){
            return "redirect:/movie";
        }
        model.addAttribute("movie", movieService.getOne(id));
        return"movie-edit";
    }
    @PostMapping("/movie/{id}/edit")
    public String cinemaMovieUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam int rating, Model model){
        movieService.update(id, name, rating);
        return"redirect:/movie";
    }
    @PostMapping("/movie/{id}/remove")
    public String cinemaMovieDelete(@PathVariable(value = "id") long id, Model model){
        movieService.deleteById(id);
        return"redirect:/movie";
    }
}