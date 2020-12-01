package com.cinema.repo;

import com.cinema.models.Seance;
import org.springframework.data.repository.CrudRepository;

public interface SeanceRepository extends CrudRepository<Seance, Integer> {
}
