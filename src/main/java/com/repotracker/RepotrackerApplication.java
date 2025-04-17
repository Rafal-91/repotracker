package com.repotracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RepotrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RepotrackerApplication.class, args);
    }

}
