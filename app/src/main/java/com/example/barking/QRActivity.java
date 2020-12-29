package com.example.barking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class QRActivity extends AppCompatActivity {

    int reservation_number;
    ImageView qrImage;
    String reservation_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r);

        Intent incoming = getIntent();
        reservation_number = incoming.getIntExtra("res_number", 0);
        reservation_string = String.valueOf(reservation_number);

//        Toast.makeText(this, reservation_string, Toast.LENGTH_SHORT).show();

        GenerateQR generator = new GenerateQR();

        qrImage = findViewById(R.id.qr_code);
        qrImage.setImageBitmap(generator.Generate(reservation_string));
    }
}