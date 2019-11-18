package com.example.myapplication_02;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;


public class login extends AppCompatActivity {


    EditText et_lusername, et_lpassword;

    Button btn_signin;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        databaseHelper = new DatabaseHelper(this);

        et_lusername = findViewById(R.id.et_lusername);
        et_lpassword = findViewById(R.id.et_lpassword);

        btn_signin = findViewById(R.id.btn_signin);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_lusername.getText().toString();
                String password = et_lpassword.getText().toString();

                Boolean login = databaseHelper.CheckLogin(username, password);

                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Username/Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (login == true) {
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(com.example.myapplication_02.login.this,loginpage.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(getApplicationContext(), "Invalid password or user", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }




}