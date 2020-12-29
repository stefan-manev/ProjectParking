package com.example.barking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentLogin extends Fragment {

    EditText username_signin;
    EditText password_signin;
    Button btn_signin;
    DBhelper dBhelper;

    public String[] cities = new String[]{"Berovo", "Bitola", "Debar", "Gevgelija", "Kavadarci", "Kichevo",
            "Krushevo", "Ohrid", "Prilep", "Skopje", "Struga", "Strumica", "Shtip", "Tetovo", "Veles"};

    public Double[] lats = new Double[]{41.7061, 41.0297, 41.5198, 41.1452, 41.4329, 41.5129, 41.3706, 41.1231, 41.3441, 41.9981,
            41.1778, 41.4378, 41.7464, 42.0069, 41.7165};
    public Double[] lngs = new Double[]{22.8552, 21.3292, 20.5289, 22.4997, 22.0089, 20.9525, 21.2502, 20.8016, 21.5528, 21.4254,
            20.6783, 22.6427, 22.1997, 20.9715, 21.7723};

    public FragmentLogin() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        username_signin = (EditText) getActivity().findViewById(R.id.username_signin);
        password_signin = (EditText) getActivity().findViewById(R.id.password_signin);
        btn_signin = (Button) getActivity().findViewById(R.id.btn_signin);
        dBhelper = new DBhelper(getActivity());

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username_signin.getText().toString();
                String password = password_signin.getText().toString();

                if (user.equals("")||password.equals("")){
                    Toast.makeText(getActivity(), "All fields required.", Toast.LENGTH_SHORT).show();
                }//if left blank
                else {
                    Boolean userFound = dBhelper.checkUserPass(user, password);
                    if (userFound == true){
                        Toast.makeText(getActivity(), "Sign in successful.", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        Intent intent = new Intent(getActivity(), ReservationActivity.class);
                        intent.putExtra("lgdin_user", user);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getActivity(), "Invalid username or password.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

//        UserModel newuser = new UserModel(-1, "Test","asd");
//        Boolean userWasInserted = dBhelper.addOneUser(newuser);
//        if (userWasInserted)
//                Toast.makeText(getActivity(), "user inserted", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(getActivity(), "fail ", Toast.LENGTH_SHORT).show();

//        CityModel city = new CityModel(0, cities[0], lats[0], lngs[0]);
//        Boolean cityWasInserted = dBhelper.addOneCity(city);
//            if (cityWasInserted)
//                Toast.makeText(getActivity(), "city inserted", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(getActivity(), "fail ", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < 15; i++){
            CityModel city = new CityModel(i, cities[i], lats[i], lngs[i]);
            Boolean cityWasInserted = dBhelper.addOneCity(city);
            if (cityWasInserted)
                Toast.makeText(getActivity(), "city inserted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "fail "+i, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
