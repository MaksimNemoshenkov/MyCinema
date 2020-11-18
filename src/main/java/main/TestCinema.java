package main;

import models.Hall;
import models.Movie;
import models.Seance;
import models.Ticket;
import services.HallService;
import services.MovieService;
import services.SeanceService;
import services.TicketService;

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
        Seance seance = new Seance();
    }
}
