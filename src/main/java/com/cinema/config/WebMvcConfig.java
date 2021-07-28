package com.cinema.config;

import com.cinema.servlets.MainServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public ServletRegistrationBean getMyServletBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new MainServlet(), "/my");
        bean.setLoadOnStartup(1);
        return bean;
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}