package com.example.barking;

//import java.sql.Blob;

import java.sql.Blob;

public class ReservationModel {

    private int res_id;
    private String location;
    private String date;
    private String time;
    private String user;
//    private Blob QR;

    public ReservationModel(int res_id, String location, String date, String time, String user) {
        this.res_id = res_id;
//        this.parking_id = parking_id;
        this.location = location;
        this.date = date;
        this.time = time;
        this.user = user;
//        this.QR = QR;
    }

    public ReservationModel(){

    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

//    public int getParking_id() {
//        return parking_id;
//    }
//
//    public void setParking_id(int parking_id) {
//        this.parking_id = parking_id;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String city) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

//    public byte[] getQR() {
//        return QR;
//    }

//    public void setQR(Blob QR) {
//        this.QR = QR;
//    }

    public String toString(){
        return "Reservation: " + res_id +
//                " " + parking_id +
                " (" + location + ") " +
                " on " + date +
                "," + time +
                " by " + user + "\n";
//                "QR: " + QR;
    }
}
