package com.cinema.repo;

import com.cinema.models.Hall;
import org.springframework.data.repository.CrudRepository;

public interface HallRepository extends CrudRepository<Hall, Integer> {
}