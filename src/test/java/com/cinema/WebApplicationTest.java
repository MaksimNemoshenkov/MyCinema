package com.cinema;

import com.cinema.config.WebMvcConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class WebApplicationTest {
    @LocalServerPort
    int port;
    @Autowired
    private WebMvcConfig webMvcConfig;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Test
    public void contextLoads() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/my");
        ResponseEntity<String> response = testRestTemplate.getForEntity(uri,String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertThat(response.getHeaders().toString().contains("Date"));
    }
}