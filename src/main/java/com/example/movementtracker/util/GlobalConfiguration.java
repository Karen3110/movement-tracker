package com.example.movementtracker.util;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Represents the global configuration for the application.
 * that it contains configuration information which needs to be processed by the Spring.
 */
@Configuration
public class GlobalConfiguration {
    @Bean
    public Gson getGson() {
        return new Gson();
    }
}
