package com.football.api.footballapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableJpaRepositories
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class FootballApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballApiApplication.class, args);
    }

}
