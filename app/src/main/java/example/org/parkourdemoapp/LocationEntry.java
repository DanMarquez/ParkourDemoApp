package example.org.parkourdemoapp;

import android.location.Location;

/**
 * Created by Daniel on 7/11/2016.
 */
public class LocationEntry {
    Location location;
    String motionType;
    String positionType;

    public LocationEntry(Location location, String motionType, String positionType) {
        this.location = location;
        this.motionType = motionType;
        this.positionType = positionType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getMotionType() {
        return motionType;
    }

    public void setMotionType(String motionType) {
        this.motionType = motionType;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    //TODO: add a toString() to get them all as a string
}
