package com.example.movementtracker.cache.config;

import com.example.movementtracker.cache.InMemoryCache;
import com.example.movementtracker.model.UserLocation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public InMemoryCache<Integer, UserLocation> createInMemoryCache() {
        return new InMemoryCache<>();
    }
}
