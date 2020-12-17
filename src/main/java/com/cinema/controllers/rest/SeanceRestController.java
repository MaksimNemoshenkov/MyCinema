package com.cinema.controllers.rest;

import com.cinema.models.Seance;
import com.cinema.services.SeanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema-seance")
public class SeanceRestController {
    private final SeanceService seanceService;
    @Autowired
    public SeanceRestController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }
    @GetMapping
    public List<Seance> list(){
        return seanceService.findAll();
    }
    @GetMapping("{id}")
    public Seance getOne(@PathVariable("id") Seance seance) {
        return seance;
    }
    @PostMapping
    public Seance create(@RequestBody Seance seance){
        return seanceService.save(seance);
    }
    @PutMapping("{id}")
    public Seance update(@PathVariable("id") Seance seanceFromDB, @RequestBody Seance seance){
        return seanceService.update(seanceFromDB, seance);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Seance seance){
        seanceService.delete(seance);
    }

}
