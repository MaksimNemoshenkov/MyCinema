package com.cinema.services;

import com.cinema.models.Hall;
import com.cinema.repo.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class HallService {
    private final HallRepository hallRepository;
    @Autowired
    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }
    public List<Hall> findAll(){
        return hallRepository.findAll();
    }
    public Hall getOne(long id){
        return hallRepository.getOne(id);
    }
    public Hall save(Hall hall){
        return hallRepository.save(hall);
    }
    public Hall update(Hall hallFromDB, Hall hall){
        hallFromDB.setName(hall.getName());
        return hallRepository.save(hallFromDB);
    }
    public Hall update(long id, String name){
        Hall hall = hallRepository.getOne(id);
        hall.setName(name);
        return hallRepository.save(hall);
    }
    public void delete(Hall hall){
        hallRepository.delete(hall);
    }
    public void deleteById(Long id){
        hallRepository.deleteById(id);
    }
    public Boolean existsById(Long id){
        return hallRepository.existsById(id);
    }
}
