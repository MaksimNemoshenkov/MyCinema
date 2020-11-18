package dao;

import models.Seance;

import java.util.List;

public interface SeanceDAO {
    Seance findById (int id);
    void save (Seance seance);
    void update (Seance seance);
    void delete (Seance seance);
    List<Seance> findAll();
}
