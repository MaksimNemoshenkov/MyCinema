package services;

import dao.SeanceDAO;
import dao.SeanceDAOImpl;
import models.Seance;

import java.util.List;

public class SeanceService {
    private SeanceDAO seanceDAO = new SeanceDAOImpl();

    public SeanceService(){}

    public Seance findSeance(int id) {
        return seanceDAO.findById(id);
    }

    public void saveSeance(Seance seance) {
        seanceDAO.save(seance);
    }

    public void deleteSeance(Seance seance) {
        seanceDAO.delete(seance);
    }

    public void updateSeance(Seance seance) {
        seanceDAO.update(seance);
    }

    public List<Seance> findAllSeance() {
        return seanceDAO.findAll();
    }
}
