package com.cinema.services;

import com.cinema.controllers.rest.HallRestController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HallServiceImplTest {
    @Autowired
    HallRestController hallRestController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void save() {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).contains("Hello");
    }
}