package com.example.movementtracker;

import com.example.movementtracker.existingresources.ExistingResource;
import com.example.movementtracker.model.UserLocation;
import com.example.movementtracker.model.response.DistanceResponse;
import com.example.movementtracker.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LocationService locationService;

    @Test
    public void testPostAndCalculateDistance() throws Exception {
        UserLocation userLocation1 = new UserLocation(ExistingResource.userId, -75.0, 45.0, new Date());
        UserLocation userLocation2 = new UserLocation(ExistingResource.userId, -45.0, 35.0, new Date());
        DistanceResponse expectedDistance = new DistanceResponse(ExistingResource.userId, new Date(), new Date(), 2766.3027407722584);


        // Posting location1
        this.mockMvc.perform(
                        post("/controller/emit").contentType("application/json").content(objectMapper.writeValueAsString(userLocation1)))
                .andExpect(status().isOk());

        // Posting location2
        this.mockMvc.perform(
                        post("/controller/emit").contentType("application/json").content(objectMapper.writeValueAsString(userLocation2)))
                .andExpect(status().isOk());

        // 5 seconds to make sure that kafka consumer will be triggered.
        Thread.sleep(5000);
        // Calling location service to calculate the distance
        DistanceResponse actualDistance = locationService.calculateDistance(ExistingResource.userId);
        assertEquals(expectedDistance.getDistanceInKm(), actualDistance.getDistanceInKm());
    }
}
