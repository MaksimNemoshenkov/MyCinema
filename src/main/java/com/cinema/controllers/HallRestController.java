package com.cinema.controllers;

import com.cinema.models.Hall;
import com.cinema.repo.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema-hall")
public class HallRestController {
    private final HallRepository hallRepository;

    @Autowired
    public HallRestController(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }
    @GetMapping
    public List<Hall> list(){
        return hallRepository.findAll();
    }
    @GetMapping("{id}")
    public Hall getOne(@PathVariable("id") Hall hall) {
        return hall;
    }
    @PostMapping
    public Hall create(@RequestBody Hall hall){
        return hallRepository.save(hall);
    }
    @PutMapping("{id}")
    public Hall update(@PathVariable("id") Hall hallFromDB, @RequestBody Hall hall){
        hallFromDB.setName(hall.getName());
        return hallRepository.save(hallFromDB);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Hall hall){
        hallRepository.delete(hall);
    }
}