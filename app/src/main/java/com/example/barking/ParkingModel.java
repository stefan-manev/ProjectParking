package com.example.barking;

public class ParkingModel {

    private int pid;
    private String parking_name;
    private String location;
    private int free_spaces;
    private Double latitude;
    private Double longitude;
    private int city;

    public ParkingModel(int pid, String parking_name, String location, Double latitude, Double longitude, int city) {
        this.pid = pid;
        this.parking_name = parking_name;
        this.location = location;
        this.free_spaces = 150;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public ParkingModel(int pid, String parking_name, Double latitude, Double longitude, int city) {
        this.pid = pid;
        this.parking_name = parking_name;
        this.location = location;
        this.free_spaces = 150;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public ParkingModel(){}

    public String toString(){
        return "Parking: " + pid +
                " " + parking_name +
                " (" + location + ") " +
                " @ " + latitude +
                "," + longitude +
                " in " + city + "\n" +
                "Spaces: " + free_spaces;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getParkingName() {
        return parking_name;
    }

    public void setParkingName(String parking_name) {
        this.parking_name = parking_name;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public int getFreeSpaces() {
        return free_spaces;
    }

    public void setFreeSpaces(int free_spaces) {
        this.free_spaces = free_spaces;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }
}
