package com.example.barking;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SummaryFragment extends Fragment {

    TextView details_tv;
    String res_no;
    String p_name;
    String date;
    String time;
    Button qr_btn;
    Button navigate;
    Button my_res;
    
    Button cancel;

    DBhelper dBhelper;

    String lgdin_usr;
    int reservation_number;

    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dBhelper = new DBhelper(getActivity());

        Intent incoming = getActivity().getIntent();
        res_no = incoming.getStringExtra("res_no");
        p_name = incoming.getStringExtra("location");
        date = incoming.getStringExtra("date");
        time = incoming.getStringExtra("time");
        lgdin_usr = incoming.getStringExtra("lgdin_user");

        qr_btn = (Button) getActivity().findViewById(R.id.btn_qr2);
        navigate = (Button) getActivity().findViewById(R.id.btn_navigate2);
//        my_res = (Button) getActivity().findViewById(R.id.btn_my_res2);
        details_tv = (TextView) getActivity().findViewById(R.id.details_tv);
        cancel = (Button) getActivity().findViewById(R.id.btn_cancel); 

        details_tv.setText("1 parking spot\nat " + p_name + "\non " + date + "\nat " + time);

        Cursor cursor = dBhelper.getParkingByName(p_name);
        cursor.moveToNext();
        String lat = cursor.getString(cursor.getColumnIndex("latitude"));
        String lng = cursor.getString(cursor.getColumnIndex("longitude"));

        reservation_number = Integer.parseInt(res_no);


        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), lat+lng, Toast.LENGTH_SHORT).show();
                String myUri = "google.navigation:q="+lat+","+lng+"&mode=d";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(myUri));
                Intent chooser = Intent.createChooser(intent, "Launch Maps");
                startActivity(chooser);
            }
        });

        qr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent QRintent = new Intent(getActivity(), QRActivity.class);
                QRintent.putExtra("res_number", reservation_number);
                startActivity(QRintent);
            }
        });
        
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dBhelper.deleteClickedReservation(String.valueOf(reservation_number));
                Toast.makeText(getActivity(), "Reservation cancelled", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MyResActivity.class);
                intent.putExtra("lgdin_user", lgdin_usr);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }
}