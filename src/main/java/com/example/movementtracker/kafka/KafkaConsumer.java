package com.example.movementtracker.kafka;

import com.example.movementtracker.cache.InMemoryCache;
import com.example.movementtracker.kafka.config.KafkaConfigConstants;
import com.example.movementtracker.model.UserLocation;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * A service class for consuming messages from a Kafka topic. Utilizes an in-memory cache to store UserLocation objects.
 */
@Service
public class KafkaConsumer {

    @Autowired
    private InMemoryCache<Integer, UserLocation> inMemoryCache;

    @Autowired
    private Gson gson;

    @KafkaListener(topics = KafkaConfigConstants.TOPIC_NAME, groupId = KafkaConfigConstants.GROUP_ID)
    public void consume(String message) {
        UserLocation userLocation = gson.fromJson(message, UserLocation.class);
        inMemoryCache.put(userLocation.getUserId(), userLocation);
    }
}
