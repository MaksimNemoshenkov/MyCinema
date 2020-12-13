package com.cinema.controllers.rest;

import com.cinema.models.Hall;
import com.cinema.services.HallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("cinema-hall")
public class HallRestController {
    private final HallServiceImpl hallService;
    @Autowired
    public HallRestController(HallServiceImpl hallService) {
        this.hallService = hallService;
    }
    @GetMapping
    public List<Hall> list(){
        return hallService.findAll();
    }
    //@PathVariable(name = "id") int id
    @GetMapping("{id}")
    public Hall getOne(@PathVariable(name = "id")Long id, Hall hall) {
        return hallService.getOne(id);
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