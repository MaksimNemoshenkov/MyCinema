package com.cinema.services;

import com.cinema.models.Hall;
import com.cinema.models.Movie;
import com.cinema.models.Seance;
import com.cinema.repo.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeanceService {
    private final SeanceRepository seanceRepository;
    private final MovieService movieService;
    private final HallService hallService;
    @Autowired
    public SeanceService(SeanceRepository seanceRepository, MovieService movieService, HallService hallService) {
        this.seanceRepository = seanceRepository;
        this.movieService = movieService;
        this.hallService = hallService;
    }

    public List<Seance> findAll() {
        return seanceRepository.findAll();
    }

    public Seance save(Seance seance) {
        seanceRepository.save(seance);
        return seance;
    }
    public void save(String date, long hallId, long movieId){
        Seance seance = new Seance(date);
        Hall hall = hallService.getOne(hallId);
        seance.setHall(hall);
        Movie movie = movieService.getOne(movieId);
        seance.setMovie(movie);
        save(seance);
    }
    public boolean existsById(long id) {
        return seanceRepository.existsById(id);
    }

    public Seance getOne(long id) {
        return seanceRepository.getOne(id);
    }

    public void deleteById(long id) {
        seanceRepository.deleteById(id);
    }
    public void update(long id, String date, long hallId, long movieId) {
        Seance seance = getOne(id);
        seance.setDate(date);
        Hall hall = hallService.getOne(hallId);
        seance.setHall(hall);
        Movie movie = movieService.getOne(movieId);
        seance.setMovie(movie);
        save(seance);
    }

    public Seance update(Seance seanceFromDB, Seance seance) {
        seanceFromDB.setDate(seance.getDate());
        seanceFromDB.setMovie(seance.getMovie());
        seanceFromDB.setHall(seance.getHall());
        return save(seance);
    }

    public void delete(Seance seance) {
        seanceRepository.delete(seance);
    }
}
