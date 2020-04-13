package com.map.geotag.model;

import java.io.Serializable;

public class Location implements Serializable {

    public static final String TABLE_NAME = "employee";

    public static final String KEY_ID = "id";
    public static final String KEY_LAT = "latitude";
    public static final String KEY_LONG = "longitude";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_FILE = "file";

    private String id, lat, longi, address, file;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
