package example.org.parkourdemoapp;

import android.location.Location;

/**
 * Created by Daniel on 7/11/2016.
 */
public class VenueEntry {
    String venue;
    String address;
    String categoryOne;
    String categoryTwo;
    Double distance;
    Location userLocation;

    public VenueEntry(String venue, String address, String categoryOne, String categoryTwo, Double distance, Location userLocation) {
        this.venue = venue;
        this.address = address;
        this.categoryOne = categoryOne;
        this.categoryTwo = categoryTwo;
        this.distance = distance;
        this.userLocation = userLocation;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategoryOne() {
        return categoryOne;
    }

    public void setCategoryOne(String categoryOne) {
        this.categoryOne = categoryOne;
    }

    public String getCategoryTwo() {
        return categoryTwo;
    }

    public void setCategoryTwo(String categoryTwo) {
        this.categoryTwo = categoryTwo;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }


    //TODO: add a toString() to get them all as a string
}
