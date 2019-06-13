package com.aazizova.springboottesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Application.
 */
@SpringBootApplication
@EnableResourceServer
public class Application {
    /**
     * Main.
     *
     * @param args String[]
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
