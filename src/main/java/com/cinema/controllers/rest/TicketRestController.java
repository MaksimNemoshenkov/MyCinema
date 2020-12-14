package com.cinema.controllers.rest;

import com.cinema.models.Ticket;
import com.cinema.services.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema-ticket")
public class TicketRestController {
    private final TicketService ticketService;
    @Autowired
    public TicketRestController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping
    public List<Ticket> list(){
        return ticketService.findAll();
    }
    @GetMapping("{id}")
    public Ticket getOne(@PathVariable("id") Ticket ticket) {
        return ticket;
    }
    @PostMapping
    public Ticket create(@RequestBody Ticket ticket){
        return ticketService.save(ticket);
    }
    @PutMapping("{id}")
    public Ticket update(@PathVariable("id") Ticket ticketFromDB, @RequestBody Ticket ticket){
        return ticketService.update(ticketFromDB, ticket);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Ticket ticket){
        ticketService.delete(ticket);
    }
    
}
