package com.cinema.controllers;

import com.cinema.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {
    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping("/tickets")
    public String tickets(Model model){
        model.addAttribute("ticket", ticketService.findAll());
        return "ticket-main";
    }
    @GetMapping("/ticket/add")
    public String ticketAdd(Model model){
        return "ticket-add";
    }
    @PostMapping("/ticket/add")
    public String cinemaTicketAdd(@RequestParam int place, @RequestParam long seanceId, Model model){
        ticketService.save(place, seanceId);
        return"redirect:/tickets";
    }

    @GetMapping("/ticket/{id}")
    public String ticketDetails(@PathVariable(value = "id") long id, Model model){
        if(!ticketService.existsById(id)){
            return "redirect:/tickets";
        }
        model.addAttribute("ticket", ticketService.getOne(id));
        return"ticket-detail";
    }

    @GetMapping("/ticket/{id}/edit")
    public String ticketEdit(@PathVariable(value = "id") long id, Model model){
        if(!ticketService.existsById(id)){
            return "redirect:/tickets";
        }
        model.addAttribute("ticket", ticketService.getOne(id));
        return"ticket-edit";
    }

    @PostMapping("/ticket/{id}/edit")
    public String cinemaTicketUpdate(@PathVariable(value = "id") long id, @RequestParam int place, @RequestParam long seanceId, Model model){
        ticketService.update(id, place, seanceId);
        return"redirect:/tickets";
    }

    @PostMapping("/ticket/{id}/remove")
    public String cinemaTicketDelete(@PathVariable(value = "id") long id, Model model){
        ticketService.deleteById(id);
        return"redirect:/tickets";
    }
}
