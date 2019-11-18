package com.example.myapplication_02;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    CheckBox c1;

    EditText et_username, et_password, et_cpassword;

    Button btn_register, btn_login;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);



        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        et_cpassword = (EditText)findViewById(R.id.et_cpassword);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_login = (Button)findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);

            }
        });



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=et_username.getText().toString();
                String password=et_password.getText().toString();
                String c_password=et_cpassword.getText().toString();

                if (username.equals("")||password.equals("")||c_password.equals("")){

                    Toast.makeText(getApplicationContext(),"Empty Fields",Toast.LENGTH_SHORT).show();

                }else{

                    if (password.equals(c_password)){

                        Boolean checkusername=databaseHelper.CheckUsername(username);

                        if (checkusername==true){

                            Boolean Insert=databaseHelper.Insert(username,password);
                            if (Insert==true) {

                                Toast.makeText(getApplicationContext(),"Register Succcess ",Toast.LENGTH_SHORT).show();

                                et_username.setText("");
                                et_password.setText("");
                                et_cpassword.setText("");
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Username Already Taken",Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Password mismatch",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
    }
}