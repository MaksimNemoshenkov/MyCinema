package com.cinema.services;

import com.cinema.models.Seance;

import java.util.List;

public interface SeanceService {
    Seance getOne(Long id);
    List<Seance> findAll();
    void save(String date, Long hallId, Long movieId);
    Seance save(Seance Seance);
    Seance update(Long id, String date, Long hallId, Long movieId);
    Seance update(Seance seanceFromDB, Seance seance);
    void deleteById(Long id);
    void delete(Seance seance);
    Boolean existsById(Long id);
}