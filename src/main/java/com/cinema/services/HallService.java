package com.cinema.services;

import com.cinema.models.Hall;

import java.util.List;

public interface HallService {
    Hall getOne(Long id);
    Hall save(Hall hall);
    Hall save(String name);
    List<Hall> findAll();
    Hall update(Hall hallFromDb, Hall hall);
    Hall update(Long id, String name);
    void deleteById(Long id);
    void delete(Hall hall);
    Boolean existsById(Long id);
}
