package com.cinema.services;

import com.cinema.servlets.MainServlet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class MainServletTest {
    @Autowired
    private MainServlet servlet;

    @Test
    public void doGetTest(){

    }

}
