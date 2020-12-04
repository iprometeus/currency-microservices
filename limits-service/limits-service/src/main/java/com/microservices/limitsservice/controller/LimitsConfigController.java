package com.microservices.limitsservice.controller;

import com.microservices.limitsservice.config.Configuration;
import com.microservices.limitsservice.model.LimitConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigController {

    private final Configuration configuration;

    public LimitsConfigController(Configuration configuration) {
        this.configuration = configuration;
    }

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfig() {
        return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }
}
