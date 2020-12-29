package com.example.barking;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class HomeActivity extends AppCompatActivity implements CityAdapter.OnCityListener {

    String passedDate;
    String passedTime;

    DBhelper dBhelper;
    RecyclerView recycler;
    ArrayList<String> city_id, city_name;
    CityAdapter cityAdapter;

    Button my_res;

    TextView pickedForTv;

    String lgdin_usr;

    public String[] parkings = new String[]{"Berovo Center", "Berovo Outskirts", "Bitola Center", "Bitola Outskirts", "Debar Center", "Gevgelija Center", "Kavadarci Center", "Kichevo Center",
            "Krushevo Center", "Ohrid Center", "Ohrid Outskirts", "Prilep Center", "Skopje Center", "Skopje Outskirts", "Struga Center", "Strumica Center", "Strumica Outskirts", "Shtip Center", "Tetovo Center",
            "Tetovo Outskirts", "Veles Center"};

    public String[] locs = new String[]{"Center", "Outskirts", "Center", "Outskirts", "Center", "Center", "Center", "Center",
            "Center", "Center", "Outskirts", "Center", "Center", "Outskirts", "Center", "Center", "Outskirts", "Center", "Center",
            "Outskirts", "Center"};

    public int[] city_links = new int[]{1, 1, 2, 2, 3, 4, 5, 6,
    7, 8, 8, 9, 10, 10, 11, 12, 12, 13, 14,
    14, 15};

    public Double[] p_lats = new Double[]{41.7061, 41.7061, 41.0297, 41.0297, 41.5198, 41.1452, 41.4329, 41.5129, 41.3706, 41.1231, 41.1231, 41.3441, 41.9981, 41.9981,
            41.1778, 41.4378, 41.4378, 41.7464, 42.0069, 42.0069, 41.7165};
    public Double[] p_lngs = new Double[]{22.8552, 22.8552, 21.3292, 21.3292, 20.5289, 22.4997, 22.0089, 20.9525, 21.2502, 20.8016, 41.1231, 21.5528, 21.4254, 21.4254,
            20.6783, 22.6427, 22.6427, 22.1997, 20.9715, 20.9715, 21.7723};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dBhelper = new DBhelper(this);
//        lv_users = (ListView) findViewById(R.id.lv_users);
        recycler = (RecyclerView) findViewById(R.id.rv_city_list);

        Intent incoming = getIntent();
        passedDate = incoming.getStringExtra("date");
        passedTime = incoming.getStringExtra("time");
        lgdin_usr = incoming.getStringExtra("lgdin_user");

        my_res = (Button) findViewById(R.id.btn_my_res);
        my_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MyResActivity.class);
                intent.putExtra("lgdin_user", lgdin_usr);
                startActivity(intent);
            }
        });

//        Toast.makeText(this, "date: "+passedDate+"\ntime: "+passedTime, Toast.LENGTH_SHORT).show();
        pickedForTv = (TextView) findViewById(R.id.picking_for_tv);
        pickedForTv.setText("On " + passedDate + " at " + passedTime);

        city_id = new ArrayList<>();
        city_name = new ArrayList<>();

        storeCitiesInArrays();

        cityAdapter = new CityAdapter(HomeActivity.this, city_id, city_name, this);
        recycler.setAdapter(cityAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        for (int i = 0; i < 21; i++){
            ParkingModel parking = new ParkingModel(i+1, parkings[i], locs[i], p_lats[i], p_lngs[i], city_links[i]);
            Boolean parkingWasInserted = dBhelper.addOneParking(parking);
            if (parkingWasInserted)
                Toast.makeText(this, "parking inserted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "fail "+i, Toast.LENGTH_SHORT).show();
        }

    }

//    private void ShowUsersOnListView(DBhelper dBhelper) {
//        userAA = new ArrayAdapter<UserModel>(this, android.R.layout.simple_list_item_1, dBhelper.getUsers());
//        lv_users.setAdapter(userAA);
//    }

//    public void goToMyReservations () {
//        Intent intent = new Intent(this, MyResActivity.class);
//        intent.putExtra("lgdin_user", lgdin_usr);
//        startActivity(intent);
//    }

    private void storeCitiesInArrays(){
        Cursor cursor = dBhelper.getTheCities();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No cities", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                city_id.add(cursor.getString(0));
                city_name.add(cursor.getString(1));
            }
        }
    }

//    public int[] city_links = new int[]{1, 1, 2, 2, 3, 4, 5, 6,
//            7, 8, 8, 9, 10, 10, 11, 12, 12, 13, 14,
//            14, 15};

    @Override
    public void onCityClick(int position) {
        city_name.get(position);
        position = position + 1;
        Intent intent = new Intent(this, ParkingsActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("date", passedDate);
        intent.putExtra("time", passedTime);
        intent.putExtra("lgdin_user", lgdin_usr);
        startActivity(intent);
    }
}