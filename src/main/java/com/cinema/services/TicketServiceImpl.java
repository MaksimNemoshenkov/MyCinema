package com.cinema.services;

import com.cinema.exceptions.NoDataFoundException;
import com.cinema.exceptions.NotFoundException;
import com.cinema.models.Seance;
import com.cinema.models.Ticket;
import com.cinema.repo.SeanceRepository;
import com.cinema.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketServiceImpl implements TicketService{
    private final TicketRepository ticketRepository;
    private final SeanceRepository seanceService;
    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, SeanceRepository seanceService) {
        this.ticketRepository = ticketRepository;
        this.seanceService = seanceService;
    }
    @Override
    public List<Ticket> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();
        if(tickets.isEmpty()){
            throw new NoDataFoundException("ticket");
        }
        return tickets;
    }
    @Override
    public Boolean existsById(Long id) {
        if(!ticketRepository.existsById(id)){
            throw new NotFoundException("ticket", id);
        }
        return true;
    }
    @Override
    public Ticket getOne(Long id) {
        existsById(id);
        return ticketRepository.getOne(id);
    }
    @Override
    public void deleteById(Long id) {
        existsById(id);
        ticketRepository.deleteById(id);
    }
    @Override
    public Ticket save(Integer place, Long seanceId){
        Seance seance = seanceService.getOne(seanceId);
        Ticket ticket = new Ticket(place);
        ticket.setSeance(seance);
        return save(ticket);
    }
    @Override
    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }
    @Override
    public Ticket update(Long id, Integer place, Long seanceId) {
        Ticket ticket = getOne(id);
        ticket.setPlace(place);
        ticket.setSeance(seanceService.getOne(seanceId));
        return save(ticket);
    }
    @Override
    public void delete(Ticket ticket) {
        ticketRepository.delete(ticket);
    }
    @Override
    public Ticket update(Ticket ticketFromDB, Ticket ticket) {
        ticketFromDB.setPlace(ticket.getPlace());
        ticketFromDB.setSeance(ticket.getSeance());
        return save(ticket);
    }
}