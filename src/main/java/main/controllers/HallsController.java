package main.controllers;

import models.Hall;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import services.HallService;

@Controller
public class HallsController {

    HallService hallService = new HallService();

    @GetMapping("/halls")
    public String halls(Model model){
       Iterable<Hall> halls = hallService.findAllHall();
       model.addAttribute("halls", halls);
        return "halls-main";
    }

    @GetMapping("/halls/add")
    public String hallsAdd(Model model){
        return"halls-add";
    }

    @PostMapping("/halls/add")
    public String hallsPostAdd(@RequestParam String title, Model model){
        Hall hall = new Hall(title);
        hallService.saveHall(hall);
        return"redirect:/halls";
    }
}
