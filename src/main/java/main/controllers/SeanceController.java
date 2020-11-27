package main.controllers;

import models.Seance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.SeanceService;

@Controller
public class SeanceController {

   // @Autowired
    private SeanceService seanceService = new SeanceService();

    @GetMapping("/seance")
    public String Seance(Model model){
        Iterable<Seance> seances = seanceService.findAllSeance();
        model.addAttribute("seances", seances);
        return "seance-main";
    }
}