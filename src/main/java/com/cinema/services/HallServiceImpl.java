package com.cinema.services;

import com.cinema.exceptions.NoDataFoundException;
import com.cinema.exceptions.NotFoundException;
import com.cinema.models.Hall;
import com.cinema.repo.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HallServiceImpl implements HallService{
    private final HallRepository hallRepository;
    @Autowired
    public HallServiceImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }
    @Override
    public List<Hall> findAll(){
       List<Hall> halls = hallRepository.findAll();
        if (halls.isEmpty()) {
            throw new NoDataFoundException("halls");
        }
        return halls;
    }
    @Override
    public Hall getOne(Long id){
        existsById(id);
        return hallRepository.getOne(id);
    }
    public Hall addHall(Hall hall) {
        existsById(hall.getId());
        return save(hall);
    }
    @Override
    public Hall save(Hall hall){
        return hallRepository.save(hall);
    }
    @Override
    public Hall save(String name) {
        return save(new Hall(name));
    }
    @Override
    public Hall update(Hall hallFromDB, Hall hall){
        hallFromDB.setName(hall.getName());
        return hallRepository.save(hallFromDB);
    }
    @Override
    public Hall update(Long id, String name){
        Hall hall = getOne(id);
        hall.setName(name);
        return hallRepository.save(hall);
    }
    @Override
    public void delete(Hall hall){
        hallRepository.delete(hall);
    }
    @Override
    public void deleteById(Long id){
        existsById(id);
        hallRepository.deleteById(id);
    }
    @Override
    public Boolean existsById(Long id){
        if(!hallRepository.existsById(id)){
            throw new NotFoundException("hall", id);
        }
        return true;
    }
}
