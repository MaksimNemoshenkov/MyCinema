package com.cinema.controllers;

import com.cinema.models.Seance;
import com.cinema.models.Ticket;
import com.cinema.repo.SeanceRepository;
import com.cinema.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private SeanceRepository seanceRepository;

    @GetMapping("/tickets")
    public String tickets(Model model){
        model.addAttribute("ticket", ticketRepository.findAll());
        return "ticket-main";
    }
    @GetMapping("/ticket/add")
    public String ticketAdd(Model model){
        return "ticket-add";
    }

    @PostMapping("/ticket/add")
    public String cinemaTicketAdd(@RequestParam int place, @RequestParam int seance_id, Model model){
        Seance seance = seanceRepository.findById(seance_id).orElseThrow();
        Ticket ticket = new Ticket(place);
        ticket.setSeance(seance);
        ticketRepository.save(ticket);
        return"redirect:/tickets";
    }

    @GetMapping("/ticket/{id}")
    public String ticketDetails(@PathVariable(value = "id") int id, Model model){
        if(!ticketRepository.existsById(id)){
            return "redirect:/tickets";
        }
        model.addAttribute("ticket", ticketRepository.findById(id).orElseThrow());
        return"ticket-detail";
    }

    @GetMapping("/ticket/{id}/edit")
    public String ticketEdit(@PathVariable(value = "id") int id, Model model){
        if(!ticketRepository.existsById(id)){
            return "redirect:/tickets";
        }
        model.addAttribute("ticket", ticketRepository.findById(id).orElseThrow());
        return"ticket-edit";
    }

    @PostMapping("/ticket/{id}/edit")
    public String cinemaTicketUpdate(@PathVariable(value = "id") int id, @RequestParam int place, @RequestParam int seance_id, Model model){
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        ticket.setPlace(place);
        ticket.setSeance(seanceRepository.findById(seance_id).orElseThrow());
        ticketRepository.save(ticket);
        return"redirect:/tickets";
    }

    @PostMapping("/ticket/{id}/remove")
    public String cinemaTicketDelete(@PathVariable(value = "id") int id, Model model){
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        ticketRepository.delete(ticket);
        return"redirect:/tickets";
    }
}
