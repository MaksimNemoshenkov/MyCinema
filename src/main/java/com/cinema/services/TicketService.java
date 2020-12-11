package com.cinema.services;

import com.cinema.models.Seance;
import com.cinema.models.Ticket;
import com.cinema.repo.SeanceRepository;
import com.cinema.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SeanceRepository seanceService;
    @Autowired
    public TicketService(TicketRepository ticketRepository, SeanceRepository seanceService) {
        this.ticketRepository = ticketRepository;
        this.seanceService = seanceService;
    }
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
    public boolean existsById(long id) {
        return ticketRepository.existsById(id);
    }
    public Ticket getOne(long id) {
        return ticketRepository.getOne(id);
    }

    public void deleteById(long id) {
        ticketRepository.deleteById(id);
    }
    public Ticket save(int place, long seanceId){
        Seance seance = seanceService.getOne(seanceId);
        Ticket ticket = new Ticket(place);
        ticket.setSeance(seance);
/*        seance.addTicket(ticket);
        seanceService.save(seance);*/
        return save(ticket);
    }
    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }
    public Ticket update(long id, int place, long seanceId) {
        Ticket ticket = ticketRepository.getOne(id);
        ticket.setPlace(place);
        ticket.setSeance(seanceService.getOne(seanceId));
        return save(ticket);
    }
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }

    public Ticket update(Ticket ticketFromDB, Ticket ticket) {
        ticketFromDB.setPlace(ticket.getPlace());
        ticketFromDB.setSeance(ticket.getSeance());
        return save(ticket);
    }
}