package com.cinema.services;

import com.cinema.exceptions.NoDataFoundException;
import com.cinema.exceptions.NotFoundException;
import com.cinema.models.Hall;
import com.cinema.models.Movie;
import com.cinema.models.Seance;
import com.cinema.repo.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeanceServiceImpl implements SeanceService{
    private final SeanceRepository seanceRepository;
    private final MovieService movieService;
    private final HallService hallService;
    @Autowired
    public SeanceServiceImpl(SeanceRepository seanceRepository, MovieService movieService, HallService hallService) {
        this.seanceRepository = seanceRepository;
        this.movieService = movieService;
        this.hallService = hallService;
    }
    @Override
    public List<Seance> findAll() {
        List<Seance> seances = seanceRepository.findAll();
        if(seances.isEmpty()){
            throw new NoDataFoundException("seance");
        }
        return seances;
    }
    @Override
    public Seance save(Seance seance) {
        seanceRepository.save(seance);
        return seance;
    }
    @Override
    public void save(String date, Long hallId, Long movieId){
        Seance seance = new Seance(date);
        Hall hall = hallService.getOne(hallId);
        seance.setHall(hall);
        Movie movie = movieService.getOne(movieId);
        seance.setMovie(movie);
        save(seance);
    }
    @Override
    public Boolean existsById(Long id) {
        if(!seanceRepository.existsById(id)){
            throw new NotFoundException("movie", id);
        }
        return true;
    }
    @Override
    public Seance getOne(Long id) {
        existsById(id);
        return seanceRepository.getOne(id);
    }
    @Override
    public void deleteById(Long id) {
        existsById(id);
        seanceRepository.deleteById(id);
    }
    @Override
    public Seance update(Long id, String date, Long hallId, Long movieId) {
        Seance seance = getOne(id);
        seance.setDate(date);
        Hall hall = hallService.getOne(hallId);
        seance.setHall(hall);
        Movie movie = movieService.getOne(movieId);
        seance.setMovie(movie);
        return save(seance);
    }
    @Override
    public Seance update(Seance seanceFromDB, Seance seance) {
        seanceFromDB.setDate(seance.getDate());
        seanceFromDB.setMovie(seance.getMovie());
        seanceFromDB.setHall(seance.getHall());
        return save(seance);
    }
    @Override
    public void delete(Seance seance) {
        seanceRepository.delete(seance);
    }
}
