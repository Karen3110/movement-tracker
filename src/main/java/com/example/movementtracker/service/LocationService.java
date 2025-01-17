package com.example.movementtracker.service;

import com.example.movementtracker.cache.InMemoryCache;
import com.example.movementtracker.model.UserLocation;
import com.example.movementtracker.model.response.DistanceResponse;
import com.example.movementtracker.util.service.HaversinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * LocationService class provides functionality related to calculating distances between user locations. It utilizes an in-memory cache to
 * manipulate with user location records and the Haversine formula for distance calculation.
 */
@Service
public class LocationService {

    @Autowired
    private InMemoryCache<Integer, UserLocation> inMemoryCache;

    public DistanceResponse calculateDistance(int userId) {
        List<UserLocation> userLocationRecords = inMemoryCache.get(userId);

        if (userLocationRecords != null) {
            double distance = 0;
            UserLocation currentLocation = null;
            for (UserLocation firstPoint : userLocationRecords) {
                if (currentLocation == null) {
                    currentLocation = firstPoint;
                    continue;
                }
                double pointsDistance = HaversinService.calculateDistance(currentLocation.getLatitude(), currentLocation.getLongitude(),
                                                                          firstPoint.getLatitude(), firstPoint.getLongitude());
                distance += pointsDistance;
            }

            UserLocation firstLocation = userLocationRecords.get(0);
            UserLocation lastLocation = userLocationRecords.get(userLocationRecords.size() - 1);
            return new DistanceResponse(userId, firstLocation.getDate(), lastLocation.getDate(), distance);
        } else {
            return null;
        }
    }
}
