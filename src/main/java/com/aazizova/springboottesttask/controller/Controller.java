package com.aazizova.springboottesttask.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Anna on 02.02.2019.
 */
@RestController
public class Controller {
    private static final Logger logger = LogManager.getLogger("Controller");

    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage() {
        return appName;
    }
}
