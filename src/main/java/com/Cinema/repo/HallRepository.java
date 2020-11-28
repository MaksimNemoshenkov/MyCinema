package com.Cinema.repo;

import com.Cinema.models.Hall;
import org.springframework.data.repository.CrudRepository;

public interface HallRepository extends CrudRepository<Hall, Integer> {
}