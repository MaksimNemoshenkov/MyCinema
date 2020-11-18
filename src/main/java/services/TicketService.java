package services;

import dao.TicketDAO;
import dao.TicketDAOImpl;
import models.Ticket;

import java.util.List;

public class TicketService {
    private TicketDAO ticketDAO = new TicketDAOImpl();

    public TicketService(){}

    public Ticket findTicket(int id) {
        return ticketDAO.findById(id);
    }

    public void saveTicket(Ticket ticket) {
        ticketDAO.save(ticket);
    }

    public void deleteTicket(Ticket ticket) {
        ticketDAO.delete(ticket);
    }

    public void updateTicket(Ticket ticket) {
        ticketDAO.update(ticket);
    }

    public List<Ticket> findAllTickets() {
        return ticketDAO.findAll();
    }
}
