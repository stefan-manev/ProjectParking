package com.example.barking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QRFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QRFragment extends Fragment {

    int reservation_number;
    String reservation_string;
    ImageView qrImage;

    public QRFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Intent incoming = getActivity().getIntent();
        reservation_number = incoming.getIntExtra("res_number", 0);

        reservation_string = String.valueOf(reservation_number);

        GenerateQR generator = new GenerateQR();

        qrImage = getActivity().findViewById(R.id.qr_code);
        qrImage.setImageBitmap(generator.Generate(reservation_string));

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_q_r, container, false);
    }
}