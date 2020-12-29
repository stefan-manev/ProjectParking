package com.example.barking;

public class CityModel {

    private int cid;
    private String city;
    private Double latitude;
    private Double longitude;

    public CityModel(int cid, String city, Double latitude, Double longitude) {
        this.cid = cid + 1;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}
