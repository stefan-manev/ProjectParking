package com.example.barking;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

import java.util.Random;

public class ParkingsActivity extends AppCompatActivity implements ParkingAdapter.OnParkingListener {

    int rand_res;
    Random rand = new Random();

    DBhelper dBhelper;
    RecyclerView recycler;
    ArrayList<String> parking_id, parking_name, available;
    ParkingAdapter parkingAdapter;
    String spaces;
    String date;
    String time;
    int pos;
    int p;

    Button btn_my_res;

    String lgdin_usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rand_res = rand.nextInt(1000);

        dBhelper = new DBhelper(ParkingsActivity.this);
        recycler = (RecyclerView) findViewById(R.id.rv_parking_list);

        parking_id = new ArrayList<>();
        parking_name = new ArrayList<>();
        available = new ArrayList<>();
        p = 10;

        Intent incoming = getIntent();
        pos = incoming.getIntExtra("position", 1);
        date = incoming.getStringExtra("date");
        time = incoming.getStringExtra("time");
        lgdin_usr = incoming.getStringExtra("lgdin_user");

        btn_my_res = findViewById(R.id.btn_my_res);
        btn_my_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParkingsActivity.this, MyResActivity.class);
                intent.putExtra("lgdin_user", lgdin_usr);
                startActivity(intent);
            }
        });


        storeParkingsInArrays(pos);

        parkingAdapter = new ParkingAdapter(ParkingsActivity.this, parking_id, parking_name, available, ParkingsActivity.this);
        recycler.setAdapter(parkingAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(ParkingsActivity.this));

        Toast.makeText(this, "Got pos: " + pos, Toast.LENGTH_SHORT).show();

    }

    private void storeParkingsInArrays(int p){
        String p_name;
        Cursor cursor = dBhelper.getTheParkingsInCity(p);
        int countFree;
        String freeCount;
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No parkings", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    p_name = cursor.getString(cursor.getColumnIndex("parking_name"));
                    countFree = dBhelper.countFreeForParkingInCity(p_name);
                    freeCount = String.valueOf(countFree);
//                    countFree.moveToNext();
//                    parking_name.add(cursor.getString(cursor.getColumnIndex("parking_name")));
//                    available.add(countFree.getString(countFree.getColumnIndex("free_count")));
//                available.add(cursor.getString(cursor.getColumnIndex("free_spaces")));
                    available.add(freeCount);
                    parking_id.add(cursor.getString(cursor.getColumnIndex("parking_id")));
                    parking_name.add(p_name);
                }
            }
    }

//    public ReservationModel(int res_id, String location, String date, String time, String user, Blob QR)

    @Override
    public void onParkingClick(int position) {

        String p = parking_name.get(position);



        ReservationModel reservation = new ReservationModel(rand_res, p, date, time, lgdin_usr);
        Boolean reservationWasInserted = dBhelper.addOneReservation(reservation);

        if (reservationWasInserted)
            Toast.makeText(this, "Reservation registered", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "fail ", Toast.LENGTH_SHORT).show();


        Toast.makeText(this, "Clicked item " + pos, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra("p", p);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        intent.putExtra("lgdin_user", lgdin_usr);
        intent.putExtra("res_number", rand_res);
        startActivity(intent);
    }

}