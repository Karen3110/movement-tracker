package com.example.movementtracker.controller;

import com.example.movementtracker.kafka.KafkaProducer;
import com.example.movementtracker.model.UserLocation;
import com.example.movementtracker.model.response.DistanceResponse;
import com.example.movementtracker.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controller")
public class AppController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private LocationService locationService;

    @PostMapping(value = "/emit")
    public String publish(@RequestBody UserLocation location) {
        kafkaProducer.sendMessage(location);
        return "Message sent to kafka topic";
    }
    @GetMapping("/distance/{userId}")
    public ResponseEntity<DistanceResponse> getDistance(@PathVariable int userId){
        DistanceResponse distanceResponse = locationService.calculateDistance(userId);
        if(distanceResponse==null){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.ok(distanceResponse);
        }

    }
}
