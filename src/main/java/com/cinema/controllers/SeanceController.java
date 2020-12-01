package com.cinema.controllers;

import com.cinema.models.Hall;
import com.cinema.models.Movie;
import com.cinema.models.Seance;
import com.cinema.repo.HallRepository;
import com.cinema.repo.MovieRepository;
import com.cinema.repo.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SeanceController {

    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private HallRepository hallRepository;

    @GetMapping("/seance")
    public String seance(Model model){
        Iterable<Seance> seances = seanceRepository.findAll();
        model.addAttribute("seances", seances);
        return "seance-main";
    }
    @GetMapping("/seance/add")
    public String seanceAdd(Model model){
        return "seance-add";
    }

    @PostMapping("/seance/add")
    public String seancePostAdd (@RequestParam String date, @RequestParam int movie_id, @RequestParam int hall_id, Model model){
        Seance seance = new Seance(date);
        Hall hall = hallRepository.findById(hall_id).orElseThrow();
        seance.setHall(hall);
        Movie movie = movieRepository.findById(movie_id).orElseThrow();
        seance.setMovie(movie);
        seanceRepository.save(seance);
        return "redirect:/seance";
    }
    @GetMapping("/seance/{id}")
    public String seanceDetails(@PathVariable(value = "id") int id, Model model){
        if(!seanceRepository.existsById(id)){
            return "redirect:/seance";
        }
        model.addAttribute("seance", seanceRepository.findById(id).orElseThrow());
        return "seance-detail";
    }
    @GetMapping("/seance/{id}/edit")
    public String seanceEdit(@PathVariable(value = "id") int id, Model model){
        if(!seanceRepository.existsById(id)){
            return "redirect:/seance";
        }
        model.addAttribute("seance", seanceRepository.findById(id).orElseThrow());
        return"seance-edit";
    }
    @PostMapping("/seance/{id}/edit")
    public String cinemaSeanceUpdate(@PathVariable(value = "id") int id, @RequestParam String date, @RequestParam int movie_id, @RequestParam int hall_id, Model model){
        Seance seance = seanceRepository.findById(id).orElseThrow();
        seance.setDate(date);
        Hall hall = hallRepository.findById(hall_id).orElseThrow();
        seance.setHall(hall);
        Movie movie = movieRepository.findById(movie_id).orElseThrow();
        seance.setMovie(movie);
        seanceRepository.save(seance);
        return"redirect:/seance";
    }

    @PostMapping("/seance/{id}/remove")
    public String cinemaSeanceDelete(@PathVariable(value = "id") int id, Model model){
        seanceRepository.delete(seanceRepository.findById(id).orElseThrow());
        return"redirect:/seance";
    }
}