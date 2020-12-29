package com.example.barking;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

public class MyResActivity extends AppCompatActivity implements ResAdapter.OnResListener {

    DBhelper dBhelper;
    RecyclerView recycler;
    ArrayList<String> res_parking, res_date, res_num;
    ResAdapter resAdapter;
    String date;
//    String time;
    int pos;
    int p;

    Button btn_cancel;

    String lgdin_usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_res);

        dBhelper = new DBhelper(MyResActivity.this);
        recycler = (RecyclerView) findViewById(R.id.rv_reservation_list);

        Intent incoming = getIntent();
        lgdin_usr = incoming.getStringExtra("lgdin_user");

        res_parking = new ArrayList<>();
        res_date = new ArrayList<>();
        res_num = new ArrayList<>();
        p = 10;


        storeReservationsInArrays(lgdin_usr);

        resAdapter = new ResAdapter(MyResActivity.this, res_parking, res_date, MyResActivity.this);
        recycler.setAdapter(resAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(MyResActivity.this));


//        Toast.makeText(this, "Got pos: " + pos, Toast.LENGTH_SHORT).show();

    }

    private void storeReservationsInArrays(String u){
//        Toast.makeText(this, "User is: " + u, Toast.LENGTH_SHORT).show();
        Cursor cursor = dBhelper.getUserReservations(u);
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No reservations", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                res_parking.add(cursor.getString(cursor.getColumnIndex("location")));
                res_date.add(cursor.getString(cursor.getColumnIndex("date")));
                res_num.add(cursor.getString(cursor.getColumnIndex("res_no")));
            }
        }
    }


//    @Override
//    public void onResButtonClick(int position){
//        Boolean deleted = dBhelper.deleteClickedReservation(res_num.get(position));
//        if (deleted) {
//            Toast.makeText(this, "Reservation cancelled", Toast.LENGTH_SHORT).show();
//            storeReservationsInArrays(lgdin_usr);
//        }
//        else
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onResClick(int position) {

        //    private int res_id;
//    private String location;
//    private String date;
//    private String time;
//    private String user;

        ReservationModel resModel = dBhelper.getSpecificReservation(res_num.get(position));

        String res_id = String.valueOf(resModel.getRes_id());
        String location = resModel.getLocation();
        String date = resModel.getDate();
        String time = resModel.getTime();
        String user = resModel.getUser();

        Intent intent = new Intent(this, ResDetails.class);
        intent.putExtra("res_no", res_id);
        intent.putExtra("location", location);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        intent.putExtra("lgdin_user", lgdin_usr);
        startActivity(intent);
//        if (cursor.getCount() == 0){
//            Toast.makeText(this, "No reservations", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            while (cursor.moveToNext()){
//                res_parking.add(cursor.getString(cursor.getColumnIndex("location")));
//                res_date.add(cursor.getString(cursor.getColumnIndex("date")));
//            }
//        }
//
//        Intent summaryIntent = new Intent(this, MyResActivity.class);
//
//        startActivity(summaryIntent);
//        Toast.makeText(this, "Clicked res: " + res_num.get(position), Toast.LENGTH_SHORT).show();
//        String pos = res_parking.get(position);
//        Intent intent = new Intent(this, ConfirmationActivity.class);
//        intent.putExtra("pos", pos);
//        intent.putExtra("date", date);
//        intent.putExtra("lgdin_user", lgdin_usr);
//        startActivity(intent);
    }
}