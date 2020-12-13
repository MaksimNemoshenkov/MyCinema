package com.cinema.controllers.simple;

import com.cinema.models.Hall;
import com.cinema.services.HallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HallsController {

    private final HallServiceImpl hallService;
    @Autowired
    public HallsController(HallServiceImpl hallService) {
        this.hallService = hallService;
    }

    @GetMapping("/halls")
    public String halls(Model model){
       model.addAttribute("halls", hallService.findAll());
        return "halls-main";
    }
    @GetMapping("/halls/add")
    public String hallsAdd(Model model){
        return "hall-add";
    }
    @PostMapping("/halls/add")
    public String cinemaHallsAdd(@RequestParam String title, Model model){
        hallService.save(new Hall(title));
        return"redirect:/halls";
    }
    @GetMapping("/halls/{id}")
    public String hallDetails(@PathVariable(value = "id") long id, Model model){
/*        if(!hallService.existsById(id)){
            return "redirect:/halls";
        }*/
        model.addAttribute("hall", hallService.getOne(id));
        return"hall-detail";
    }
    @GetMapping("/halls/{id}/edit")
    public String hallEdit(@PathVariable(value = "id") long id, Model model){
/*        if(!hallService.existsById(id)){
            return "redirect:/halls";
        }*/
        model.addAttribute("hall", hallService.getOne(id));
        return"hall-edit";
    }
    @PostMapping("/halls/{id}/edit")
    public String cinemaHallUpdate(@PathVariable(value = "id") long id, @RequestParam String name, Model model){
        hallService.update(id, name);
        return"redirect:/halls";
    }
    @PostMapping("/halls/{id}/remove")
    public String cinemaHallDelete(@PathVariable(value = "id") long id, Model model){
        hallService.deleteById(id);
        return"redirect:/halls";
    }
}