package com.example.today_menu.mapdata;

import androidx.annotation.NonNull;

public class Place {
    String name;
    Double latitude;
    Double longitude;
    String address;
    String RoadAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoadAddress() {
        return RoadAddress;
    }

    public void setRoadAddress(String loadAddress) {
        this.RoadAddress = loadAddress;
    }

    @NonNull
    @Override
    public String toString() {
        return "Place{" +
                "name = " + name + "\"" +
                ", latitude= " + latitude +
                ", longitude= " + longitude +
                ", address = " + address +
                ", RoadAddress = " + RoadAddress +
                "}";
    }
}
