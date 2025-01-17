package com.example.movementtracker;

import com.example.movementtracker.existingresources.ExistingResource;
import com.example.movementtracker.model.response.DistanceResponse;
import com.example.movementtracker.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * AppRunner class is a CommandLineRunner implementation that generates random user locations, sends them to a Kafka topic using
 * KafkaProducer, and calculates the total distance based on the generated locations using LocationService. It utilizes the Haversine
 * formula for distance calculation.
 */
@Component
@EnableScheduling
public class AppRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private LocationService locationService;

    @Override
    public void run(String... args) {
    }

    @Scheduled(initialDelay = 20, fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    public void generateReports() {
        DistanceResponse distanceResponse = locationService.calculateDistance(ExistingResource.userId);
        if (distanceResponse != null) {
            LOGGER.info("-------- DISTANCE --------");
            LOGGER.info("Begin: " + distanceResponse.getDateStart());
            LOGGER.info("End: " + distanceResponse.getDateEnd());
            LOGGER.info("Distance: " + distanceResponse.getDistanceInKm());
        }
    }

}
