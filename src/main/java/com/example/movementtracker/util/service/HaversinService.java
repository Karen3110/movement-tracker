package com.example.movementtracker.util.service;

public class HaversinService {
    // Earth radius in kilometers
    private static final double EARTH_RADIUS = 6371;

    /**
     * Calculates the distance between two points on the Earth's surface using the Haversine formula.
     *
     * @param lat1
     *         Latitude of the first point in decimal degrees.
     * @param lon1
     *         Longitude of the first point in decimal degrees.
     * @param lat2
     *         Latitude of the second point in decimal degrees.
     * @param lon2
     *         Longitude of the second point in decimal degrees.
     * @return The distance between the two points in kilometers.
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Difference in coordinates
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(dLon / 2) * Math.sin(
                dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;  // Distance in kilometers
    }

}
