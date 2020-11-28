package com.Cinema.controllers;

import com.Cinema.models.Hall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.Cinema.repo.HallRepository;
import com.Cinema.services.HallService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class HallsController {

    @Autowired
    private HallRepository hallRepository;

    HallService hallService = new HallService();

    @GetMapping("/halls")
    public String halls(Model model){
     //  Iterable<Hall> halls = hallService.findAllHall(); //работает прекрасно
       Iterable<Hall> halls = hallRepository.findAll();
       model.addAttribute("halls", halls);
        return "halls-main";
    }

    @GetMapping("/halls/add")
    public String hallsAdd(Model model){
        return "halls-add";
    }

    @PostMapping("/halls/add")
    public String cinemaHallsAdd(@RequestParam String title, Model model){
        Hall hall = new Hall(title);
       // hallService.saveHall(hall); //по каким-то причинам кидает исключение
        hallRepository.save(hall);
        return"redirect:/halls";
    }
    @GetMapping("/halls/{id}")
    public String hallDetails(@PathVariable(value = "id") int id, Model model){
        if(!hallRepository.existsById(id)){
            return "redirect:/halls";
        }
        Optional<Hall> hall = hallRepository.findById(id);
        ArrayList<Hall> res =  new ArrayList<>();
        hall.ifPresent(res::add);
        model.addAttribute("hall", res);
        return"hall-detail";
    }
    @GetMapping("/halls/{id}/edit")
    public String hallEdit(@PathVariable(value = "id") int id, Model model){
        if(!hallRepository.existsById(id)){
            return "redirect:/halls";
        }
        Optional<Hall> hall = hallRepository.findById(id);
        ArrayList<Hall> res =  new ArrayList<>();
        hall.ifPresent(res::add);
        model.addAttribute("hall", res);
        return"hall-edit";
    }
    @PostMapping("/halls/{id}/edit")
    public String cinemaHallUpdate(@PathVariable(value = "id") int id, @RequestParam String title, Model model){
        Hall hall = hallRepository.findById(id).orElseThrow();
        hall.setName(title);
        hallRepository.save(hall);
        return"redirect:/halls";
    }
}