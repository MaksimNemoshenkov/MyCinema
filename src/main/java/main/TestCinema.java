package main;

import models.Hall;
import models.Movie;
import services.SeanceService;

public class TestCinema {
    public static void main(String[] args) {
        SeanceService seanceService = new SeanceService();
        Movie movie = new Movie("Limitless" , 7);
        Hall hall = new Hall();
    }
}
