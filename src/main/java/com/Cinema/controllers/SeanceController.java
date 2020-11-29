package com.Cinema.controllers;

import com.Cinema.models.Hall;
import com.Cinema.models.Movie;
import com.Cinema.models.Seance;
import com.Cinema.repo.HallRepository;
import com.Cinema.repo.MovieRepository;
import com.Cinema.repo.SeanceRepository;
import com.Cinema.utils.HibernateSessionFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
//    private SeanceService seanceService = new SeanceService();

    @GetMapping("/seance")
    public String seance(Model model){
     //   Iterable<Seance> seances = seanceService.findAllSeance();
        Iterable<Seance> seances = seanceRepository.findAll();
        model.addAttribute("seances", seances);
        return "seance-main";
    }
    @GetMapping("/seance/add")
    public String seanceAdd(Model model){
        return "seance-add";
    }

    @PostMapping("/seance/add")
    public String seancePostAdd (@RequestParam String data, @RequestParam int movie_id, @RequestParam int hall_id, Model model){
        Seance seance = new Seance(data);
        Hall hall = hallRepository.findById(hall_id).orElseThrow();
        seance.setHall(hall);
        Movie movie = movieRepository.findById(movie_id).orElseThrow();
        seance.setMovie(movie);
        seanceRepository.save(seance);
        return "redirect:/seance";
    }
}