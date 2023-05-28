package com.example.studentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableJpaRepositories("com.example.studentapp.*")
//@EntityScan("com.example.studentapp.model.*")
public class StudentAppApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(StudentAppApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(StudentAppApplication.class, args);
    }

}
