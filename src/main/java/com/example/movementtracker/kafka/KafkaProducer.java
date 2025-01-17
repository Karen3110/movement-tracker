package com.example.movementtracker.kafka;

import com.example.movementtracker.kafka.config.KafkaConfigConstants;
import com.example.movementtracker.model.UserLocation;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * A service class for sending UserLocation messages to a Kafka topic.
 */
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Gson gson;

    public void sendMessage(UserLocation userLocation) {
        kafkaTemplate.send(KafkaConfigConstants.TOPIC_NAME, gson.toJson(userLocation));
    }
}
