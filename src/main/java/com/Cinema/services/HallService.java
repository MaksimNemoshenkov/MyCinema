package com.Cinema.services;

import com.Cinema.dao.HallDAO;
import com.Cinema.dao.HallDAOImpl;

import com.Cinema.models.Hall;

import java.util.List;

public class HallService {
    private HallDAO hallDAO = new HallDAOImpl();

    public HallService(){}

    public Hall findHall(int id) {
        return hallDAO.findById(id);
    }

    public void saveHall(Hall hall) {
        hallDAO.save(hall);
    }

    public void deleteHall(Hall hall) {
        hallDAO.delete(hall);
    }

    public void updateHall(Hall hall) {
        hallDAO.update(hall);
    }

    public List<Hall> findAllHall() {
        return hallDAO.findAll();
    }
}
