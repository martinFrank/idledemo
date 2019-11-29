package com.github.martinfrank.idledemo.image;

import com.github.martinfrank.geolib.GeoPoint;

public class SubImage {

    private GeoPoint location;
    private int imageId;

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
