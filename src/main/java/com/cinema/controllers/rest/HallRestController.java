package com.cinema.controllers.rest;

import com.cinema.models.Hall;
import com.cinema.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema-hall")
public class HallRestController {
    private final HallService hallService;
    @Autowired
    public HallRestController(HallService hallService) {
        this.hallService = hallService;
    }
    @GetMapping
    public List<Hall> list(){
        return hallService.findAll();
    }
    @GetMapping("{id}")
    public Hall getOne(@PathVariable("id") Hall hall) {
        return hall;
    }
    @PostMapping
    public Hall create(@RequestBody Hall hall){
        return hallService.save(hall);
    }
    @PutMapping("{id}")
    public Hall update(@PathVariable("id") Hall hallFromDB, @RequestBody Hall hall){
        return hallService.update(hallFromDB, hall);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Hall hall){
        hallService.delete(hall);
    }
}