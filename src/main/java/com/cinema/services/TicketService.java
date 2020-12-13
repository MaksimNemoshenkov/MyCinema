package com.cinema.services;

import com.cinema.models.Ticket;

import java.util.List;

public interface TicketService {
    Ticket getOne(Long id);
    List<Ticket> findAll();
    Ticket save(Integer place, Long seanceId);
    Ticket save(Ticket Ticket);
    Ticket update(Ticket ticketFromDB, Ticket ticket);
    Ticket update(Long id, Integer place, Long seanceId);
    void deleteById(Long id);
    void delete(Ticket ticket);
    Boolean existsById(Long id);
}