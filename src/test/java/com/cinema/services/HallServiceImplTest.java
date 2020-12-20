package com.cinema.services;

import com.cinema.models.Hall;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class HallServiceImplTest {
    @Autowired
    private HallService hallService;
    @Test
    void save() {
        Hall hall = new Hall();
        Assert.assertTrue(hallService.save(hall)!=null);
    }
}