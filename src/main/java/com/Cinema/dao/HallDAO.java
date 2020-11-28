package com.Cinema.dao;

import com.Cinema.models.Hall;

import java.util.List;

public interface HallDAO {
    Hall findById (int id);
    void save (Hall hall);
    void update (Hall hall);
    void delete (Hall hall);
    List<Hall> findAll();
}
