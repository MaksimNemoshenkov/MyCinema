package com.cinema.sevlets;

import com.cinema.config.WebMvcConfig;
import org.junit.Before;
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
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MainServletTest {
    @LocalServerPort
    int port;
    @Autowired
    private WebMvcConfig webMvcConfig;
    @Autowired
    private TestRestTemplate testRestTemplate;

    private ResponseEntity<String> response;
    private URI uri;
    @Before
    public void loadResponse() throws URISyntaxException {
        uri = new URI("http://localhost:" + port + "/my");
        response = testRestTemplate.getForEntity(uri,String.class);
    }
    @Test
    public void contextLoads() throws Exception {
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    @Test
    public void containsDate(){
        assertThat(response.getHeaders().toString().contains("Date"));
    }
    @Test
    public void containsGreeting(){
        assertThat(this.testRestTemplate.getForObject(uri,String.class)).contains("Hello");
    }
}