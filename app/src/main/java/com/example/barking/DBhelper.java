package com.example.barking;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    private static final String user_table = "user";
    private static final String column_uid = "user_id";
    private static final String column_username = "username";
    private static final String column_password = "password";

    private static final String parking_table = "parking";
    private static final String column_pid  = "parking_id";
    private static final String column_pname = "parking_name";
    private static final String column_location = "location";
    private static final String column_free  = "free_spaces";
    private static final String column_lat  = "latitude";
    private static final String column_lng  = "longitude";
    private static final String column_city_link  = "city_id";

    private static final String city_table = "city";
    private static final String column_cid = "city_id";
    private static final String column_cname = "city";
    private static final String column_city_lat = "latitude";
    private static final String column_city_lng = "longitude";


    private static final String reservation_table = "reservation";
    private static final String column_res_id = "res_no";
//    private static final String column_res_parking = "parking_id";
    private static final String column_res_location = "location";
    private static final String column_res_date = "date";
    private static final String column_res_time = "time";
    private static final String column_res_user = "user";
//    private static final String column_res_QR = "QR";


    private final Context context;

//    public static final String column_plate = "plate";

    public DBhelper(@Nullable Context context) {
        super(context, "BarkDB.db", null, 2);
        this.context = context;
    }



    //    is called on first try to access database; tables are created here
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableStatement = "CREATE TABLE " + user_table + " (" +
                column_uid + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                column_username + " TEXT," +
                column_password + " TEXT" +
                ")";

        String createParkingTableStatement = "CREATE TABLE " + parking_table + " (" +
                column_pid + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                column_pname + " TEXT," +
                column_location + " TEXT," +
                column_free + " INTEGER," +
                column_lat + " DOUBLE," +
                column_lng + " DOUBLE," +
                column_city_link + " INTEGER," +
                "FOREIGN KEY (" + column_city_link + ") " +
                "REFERENCES " + city_table + "(" + column_cid + ") " +
                "ON DELETE CASCADE" +
                ")";

        String createCityTableStatement = "CREATE TABLE " + city_table + " (" +
                column_cid + " INTEGER PRIMARY KEY," +
                column_cname + " TEXT," +
                column_city_lat + " DOUBLE," +
                column_city_lng + " DOUBLE" +
                ")";

        String createReservationStatement = "CREATE TABLE " + reservation_table + " (" +
                column_res_id + " INTEGER PRIMARY KEY," +
//                column_res_parking + " INTEGER," +
                column_res_location + " TEXT," +
                column_res_date + " TEXT," +
                column_res_time + " TEXT," +
                column_res_user + " TEXT" +
//                column_res_QR + " BLOB" +
                ")";

        db.execSQL(createUserTableStatement);
        db.execSQL(createParkingTableStatement);
        db.execSQL(createCityTableStatement);
        db.execSQL(createReservationStatement);
    }


    // CITY METHODS
    public boolean addOneCity(CityModel city){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_cid, city.getCid());
        cv.put(column_cname, city.getCity());
        cv.put(column_city_lat, city.getLatitude());
        cv.put(column_city_lng, city.getLongitude());

        long insert = db.insert(city_table, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    Cursor getTheCities(){
        String q = "SELECT * FROM " + city_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(q, null);
        }
        return cursor;
    }

//    public String gimmeToast(String whatever){
//        return "This appeared: "+whatever;
//    }


    // USER METHODS
    public boolean addOneUser(UserModel userModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_username, userModel.getUsername());
        cv.put(column_password, userModel.getPassword());
//        cv.put(column_plate, userModel.getPlate_number());

        long insert = db.insert(user_table, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkUser (String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username = ?", new String[]{username});
//        String queryString = "SELECT * FROM " + user_table + " WHERE " + column_username + " = " + username;
//        cursor = db.rawQuery(queryString, null);
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public List<UserModel> getUsers() {
        List<UserModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + user_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            // loop through cursor and create new user objects; put them into returnList
            do {
                int userID = cursor.getInt(0);
                String userName = cursor.getString(1);
                String userPassword = cursor.getString(2);
//                String userPlate = cursor.getString(3);

                UserModel parsedUser = new UserModel(userID, userName, userPassword);
                returnList.add(parsedUser);

            } while (cursor.moveToNext());

        }
        else {

        }
        cursor.close();
        db.close();

        return returnList;
    }

    public Boolean checkUserPass (String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean deleteOneUser(UserModel userModel){
        SQLiteDatabase db = getWritableDatabase();
        String deleteQuery = "DELETE FROM " + user_table + " WHERE " + column_uid + " = " + userModel.getUid();
        Cursor cursor = db.rawQuery(deleteQuery, null);
        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }
    }


    // PARKING METHODS
    public boolean addOneParking(ParkingModel parkingModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_pid, parkingModel.getPid());
        cv.put(column_pname, parkingModel.getParkingName());
        cv.put(column_location, parkingModel.getLocation());
        cv.put(column_free, parkingModel.getFreeSpaces());
        cv.put(column_lat, parkingModel.getLatitude());
        cv.put(column_lng, parkingModel.getLongitude());
        cv.put(column_city_link, parkingModel.getCity());
        
        long insert = db.insert(parking_table, null, cv);
        if (insert == -1){
            Toast.makeText(context, "Failed insert", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(context, "Successful insert", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    Cursor getTheParkings(){
        String q = "SELECT * FROM " + parking_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(q, null);
        }
        return cursor;
    }

    Cursor getTheParkingsInCity(int city) {

//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * from parking where city_id = ?", city);
        String q = "SELECT * FROM " + parking_table + " WHERE " + column_city_link + " = " + city + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(q, null);
        }
        return cursor;
    }

    int countFreeForParkingInCity(String parking_name) {
        String q = "SELECT COUNT(res_no) AS 'res_count' FROM " + reservation_table +
                " WHERE " + column_res_location + " = " + "'" + parking_name + "'" + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor count = db.rawQuery(q, null);
        count.moveToNext();
        String countString = count.getString(count.getColumnIndex("res_count"));

        String p_q = "SELECT free_spaces AS 'total' FROM " + parking_table +
                " WHERE " + column_pname + " = " + "'" + parking_name + "'" + ";";
        Cursor spaces = db.rawQuery(p_q, null);
        spaces.moveToNext();
        String spacesString = spaces.getString(spaces.getColumnIndex("total"));

        int res_count = Integer.parseInt(countString);
        int total = Integer.parseInt(spacesString);

        int amount = total - res_count;

        return amount;
    }

    Cursor getParkingByName(String name) {
        String q = "SELECT * FROM " + parking_table + " WHERE " + column_pname + " = " + "'" + name + "'" + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(q, null);
        }
        return cursor;
    }

//    String getParkingLat(String name) {
//        String q = "SELECT latitude, longitude FROM " + parking_table + " WHERE " + column_pname + " = " + "'"+name+"'" + ";";
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] coords = new String[]{};
//        Cursor cursor = null;
//        if (db != null){
//            cursor = db.rawQuery(q, null);
//            coord
//        };
//    }

//    private int res_id;
//    private int parking_id;
//    private String city;
//    private String date;
//    private String time;
//    private String user;
//    private Blob QR;

//        RESERVATIONS
    public boolean addOneReservation(ReservationModel reservationModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column_res_id, reservationModel.getRes_id());
//        cv.put(column_res_parking, reservationModel.getParking_id());
        cv.put(column_res_location, reservationModel.getLocation());
        cv.put(column_res_date, reservationModel.getDate());
        cv.put(column_res_time, reservationModel.getTime());
        cv.put(column_res_user, reservationModel.getUser());
    //    cv.put(column_res_QR, reservationModel.getQR());

        long insert = db.insert(reservation_table, null, cv);
        if (insert == -1){
            Toast.makeText(context, "Failed insert", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(context, "Successful insert", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    Cursor getUserReservations(String user) {

//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("Select * from parking where city_id = ?", city);
        String q = "SELECT * FROM " + reservation_table + " WHERE " + column_res_user + " = " + "'" + user + "'" + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(q, null);
        }
        return cursor;
    }

//    private int res_id;
//    private String location;
//    private String date;
//    private String time;
//    private String user;

//        private static final String reservation_table = "reservation";
//    private static final String column_res_id = "res_no";
//    private static final String column_res_location = "location";
//    private static final String column_res_date = "date";
//    private static final String column_res_time = "time";
//    private static final String column_res_user = "user";

    ReservationModel getSpecificReservation(String res_number) {
        ReservationModel specificReservation = new ReservationModel();
        String q = "SELECT * FROM " + reservation_table + " WHERE " + column_res_id + " = " + "'" + res_number + "'" + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(q, null);
        cursor.moveToNext();
        int res_id = Integer.parseInt(res_number);
        String loc = cursor.getString(cursor.getColumnIndex("location"));
        String date = cursor.getString(cursor.getColumnIndex("date"));
        String time = cursor.getString(cursor.getColumnIndex("time"));
        String user = cursor.getString(cursor.getColumnIndex("user"));

        return new ReservationModel(res_id, loc, date, time, user);

    }

    public Boolean deleteClickedReservation(String res_number) {
        String deleteQuery = "DELETE FROM " + reservation_table + " WHERE " +
                column_res_id + " = " + res_number + ";";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(deleteQuery, null);

        if (cursor.moveToFirst())
            return true;
        else
            return false;
    }


    //    is called when the version of the database changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + user_table);
        db.execSQL("DROP TABLE IF EXISTS " + parking_table);
        db.execSQL("DROP TABLE IF EXISTS " + city_table);
        db.execSQL("DROP TABLE IF EXISTS " + reservation_table);
        onCreate(db);
    }
}
