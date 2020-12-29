package com.example.barking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView displayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    Button pickCity;
    String lgdin_usr;

    Button btn_my_res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent incoming = getIntent();
        lgdin_usr = incoming.getStringExtra("lgdin_user");

        pickCity = (Button) findViewById(R.id.btn_pick_city);
        Spinner spinner = findViewById(R.id.time_spinner);
        displayDate = (TextView) findViewById(R.id.date_tv);
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(ReservationActivity.this,
                R.array.timeslots, android.R.layout.simple_spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(timeAdapter);
        spinner.setOnItemSelectedListener(this);
        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ReservationActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        pickCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = displayDate.getText().toString();
                String time = spinner.getSelectedItem().toString();

//                Toast.makeText(ReservationActivity.this, "date: "+date+"\ntime: "+time, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReservationActivity.this, HomeActivity.class);
                intent.putExtra("date", date);
                intent.putExtra("time", time);
                intent.putExtra("lgdin_user", lgdin_usr);
                startActivity(intent);
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                m = m + 1;
                String date = y + "/" + m + "/" + d;
                displayDate.setText(date);
            }
        };

        btn_my_res = findViewById(R.id.btn_my_res);
        btn_my_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationActivity.this, MyResActivity.class);
                intent.putExtra("lgdin_user", lgdin_usr);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String timeText = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), timeText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}