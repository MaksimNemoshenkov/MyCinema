package com.cinema;

import com.cinema.config.WebMvcConfig;
import com.cinema.servlets.MainServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimeTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebMvcConfig webMvcConfig;

    @Autowired
    private MainServlet servlet;

    @Test
    public void contextLoad() throws Exception {
        this.mockMvc.perform(get("/my"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")))
                .andExpect(content().string(containsString(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()))));
    }


}
