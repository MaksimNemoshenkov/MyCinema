package com.cinema;

import com.cinema.config.WebMvcConfig;
import com.cinema.servlets.MainServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mortbay.jetty.testing.HttpTester;
import org.mortbay.jetty.testing.ServletTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WebApplicationTest {

    @Autowired
    private WebMvcConfig webMvcConfig;

    @Test
    public void addTimeInHeader(){

    }

    @Test
    public void contextLoads() throws Exception {
        ServletTester tester = new ServletTester();
        tester.addServlet(MainServlet.class, "/my");
        tester.start();

        HttpTester request = new HttpTester();
        request.setMethod("GET");
        request.setHeader("Host","tester");
        request.setURI("/my");
        request.setVersion("HTTP/1.0");

        HttpTester response = new HttpTester();
        response.parse(tester.getResponses(request.generate()));

        assertThat(response.getHeader("Date")).isNotNull();
        assertEquals(200,response.getStatus());
        assertThat(response.getContent().contains("Hello")).isTrue();
    }
}