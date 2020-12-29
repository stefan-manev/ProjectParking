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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSignup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSignup extends Fragment {

    Button btn_reg;
    EditText et_username;
    EditText et_password_register;
    EditText et_password_confirm;
    DBhelper dBhelper;

    public FragmentSignup() {
        // Required empty public constructor
    }

    public static FragmentSignup newInstance(String param1, String param2) {
        FragmentSignup fragment = new FragmentSignup();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        btn_reg = (Button) getActivity().findViewById(R.id.btn_reg);
        et_username = (EditText) getActivity().findViewById(R.id.et_username);
        et_password_register = (EditText) getActivity().findViewById(R.id.et_password_register);
        et_password_confirm = (EditText) getActivity().findViewById(R.id.et_password_confirm);
        dBhelper = new DBhelper(getActivity());

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = et_username.getText().toString();
                String password = et_password_register.getText().toString();
                String repassword = et_password_confirm.getText().toString();

                UserModel userModel;

                userModel = new UserModel(-1, user, password);

                if (user.equals("") || password.equals("") || repassword.equals("")) {
                    Toast.makeText(getActivity(), "Error creating user", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(MainActivity.this, "Error reading user: "+user+password+repassword, Toast.LENGTH_SHORT).show();
                    if (password.equals(repassword)) {
                        Boolean userExists = dBhelper.checkUser(user);
                        if (userExists == false) {
                            Boolean wasInserted = dBhelper.addOneUser(userModel);
                            if (wasInserted)
                                Toast.makeText(getActivity(), "User registered.", Toast.LENGTH_SHORT).show();
//                                ShowUsersOnListView(dBhelper);
                        }//if user doesn't exist
                        else {
                            Toast.makeText(getActivity(), "User already exists.\nConsider signing in.", Toast.LENGTH_SHORT).show();
                        }//else if user exists
                    }//passwords match
                    else {
                        Toast.makeText(getActivity(), "Passwords don't match.", Toast.LENGTH_SHORT).show();
                    }//passwords don't match
                }//all fields
            }//onClick override
        });//on-click listener

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }
}