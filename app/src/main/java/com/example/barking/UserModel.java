package com.example.barking;

public class UserModel {

    private int uid;
    private String username;
    private String password;
//    private String plate_number;

    // constructors


    public UserModel(int uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
//        this.plate_number = plate_number;
    }

    public UserModel(){}

    @Override
    public String toString() {
        return "UserModel{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
//                ", plate_number='" + plate_number + '\'' +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String surname) {
        this.password = password;
    }

//    public String getPlate_number() {
//        return plate_number;
//    }

//    public void setPlate_number(String plate_number) {
//        this.plate_number = plate_number;
//    }
}
