package dao;

import models.Ticket;

import java.util.List;

public interface TicketDAO {
    Ticket findById (int id);
    void save (Ticket ticket);
    void update (Ticket ticket);
    void delete (Ticket ticket);
    List<Ticket> findAll();
}