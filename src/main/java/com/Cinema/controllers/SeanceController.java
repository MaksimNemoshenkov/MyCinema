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
import com.Cinema.services.SeanceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class SeanceController {

    @Autowired
    private SeanceRepository seanceRepository;
/*    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private HallRepository hallRepository;*/
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
    public String seancePostAdd (@RequestParam String data, @RequestParam int movieId, @RequestParam int hallId, Model model){
        Seance seance = new Seance(data);
        Hall hall = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Hall.class, hallId);
        seance.setHall(hall);
        Movie movie = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Movie.class, movieId);
        seance.setMovie(movie);
        seanceRepository.save(seance);
        return "redirect/seance";
    }
}