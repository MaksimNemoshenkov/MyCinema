package com.cinema.controllers.simple;

import com.cinema.services.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SeanceController {
    private final SeanceService seanceService;
    @Autowired
    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping("/seance")
    public String seance(Model model){
        model.addAttribute("seances", seanceService.findAll());
        return "seance-main";
    }
    @GetMapping("/seance/add")
    public String seanceAdd(Model model){
        return "seance-add";
    }
    @PostMapping("/seance/add")
    public String seancePostAdd (@RequestParam String date, @RequestParam long movieId, @RequestParam long hallId, Model model){
        seanceService.save(date, hallId, movieId);
        return "redirect:/seance";
    }
    @GetMapping("/seance/{id}")
    public String seanceDetails(@PathVariable(value = "id") long id, Model model){
/*        if(!seanceService.existsById(id)){
            return "redirect:/seance";
        }*/
        model.addAttribute("seance", seanceService.getOne(id));
        return "seance-detail";
    }
    @GetMapping("/seance/{id}/edit")
    public String seanceEdit(@PathVariable(value = "id") long id, Model model){
/*        if(!seanceService.existsById(id)){
            return "redirect:/seance";
        }*/
        model.addAttribute("seance", seanceService.getOne(id));
        return"seance-edit";
    }
    @PostMapping("/seance/{id}/edit")
    public String cinemaSeanceUpdate(@PathVariable(value = "id") long id, @RequestParam String date, @RequestParam long movieId, @RequestParam long hallId, Model model){
        seanceService.update(id, date, hallId, movieId);
        return"redirect:/seance";
    }

    @PostMapping("/seance/{id}/remove")
    public String cinemaSeanceDelete(@PathVariable(value = "id") long id, Model model){
        seanceService.deleteById(id);
        return"redirect:/seance";
    }
}