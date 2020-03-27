package com.atsistemas.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.atsistemas.poc.*"})
@EnableJpaRepositories({"com.atsistemas.poc.persistence.repository"})
public class Launcher extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Launcher.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

}
