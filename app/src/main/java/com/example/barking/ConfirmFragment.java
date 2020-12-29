package com.example.barking;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfirmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfirmFragment extends Fragment {

    TextView res_no_tv;
    String res_no;
    String p_name;
    String date;
    String time;
    Button navigate;
    Button my_res;
    Button qr_btn;

    DBhelper dBhelper;

    String lgdin_usr;
    int reservation_number;

    // TODO: Rename parameter arguments, choose names that match


    public ConfirmFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dBhelper = new DBhelper(getActivity());

        Intent incoming = getActivity().getIntent();
        p_name = incoming.getStringExtra("p");
        date = incoming.getStringExtra("date");
        time = incoming.getStringExtra("time");
        lgdin_usr = incoming.getStringExtra("lgdin_user");
        reservation_number = incoming.getIntExtra("res_number", 0);


        my_res = (Button) getActivity().findViewById(R.id.btn_my_res);
        qr_btn = (Button) getActivity().findViewById(R.id.btn_qr);


        res_no_tv = (TextView) getActivity().findViewById(R.id.passed_tv);
        res_no_tv.setText("1 parking spot\nat " + p_name + "\non " + date + "\nat " + time);

        Cursor cursor = dBhelper.getParkingByName(p_name);
        cursor.moveToFirst();
        String lat = cursor.getString(cursor.getColumnIndex("latitude"));
        String lng = cursor.getString(cursor.getColumnIndex("longitude"));
        navigate = (Button) getActivity().findViewById(R.id.btn_navigate);

        navigate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), p_name, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), lat+lng, Toast.LENGTH_SHORT).show();
                String myUri = "geo:"+lat+","+lng;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(myUri));
//                intent.setData(Uri.parse("geo:41.0213,23.2931"));
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

        my_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyResActivity.class);
                intent.putExtra("lgdin_user", lgdin_usr);
                startActivity(intent);
            }
        });

    }
}
