package com.Cinema;

import com.Cinema.models.Hall;
import com.Cinema.models.Movie;
import com.Cinema.models.Seance;
import com.Cinema.models.Ticket;
import com.Cinema.services.HallService;
import com.Cinema.services.MovieService;
import com.Cinema.services.SeanceService;
import com.Cinema.services.TicketService;

public class TestCinema {
    public static void main(String[] args) {
        SeanceService seanceService = new SeanceService();
        HallService hallService = new HallService();
        MovieService movieService = new MovieService();
        TicketService ticketService = new TicketService();

        Movie movie = new Movie("Limitless" , 7);
        movieService.saveMovie(movie);

        Hall hall = new Hall("Green");
        hallService.saveHall(hall);

        Seance seance = new Seance("21-10-2021-18:00");
        seance.setHall(hall);
        seance.setMovie(movie);
        seanceService.saveSeance(seance);
        Ticket ticket = new Ticket(1212);
        ticket.setSeance(seance);
        seance.addTicket(ticket);
        ticketService.saveTicket(ticket);
        seanceService.updateSeance(seance);
    }
}
