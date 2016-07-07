package com.example.user.cmoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login;
        final EditText name;
        final EditText pwd;

        name = (EditText)findViewById(R.id.name);
        pwd = (EditText)findViewById(R.id.pwd);

        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String id = name.getText().toString();
                String password = pwd.getText().toString();
                Toast.makeText(Login.this, "ID : " +id, Toast.LENGTH_SHORT).show();
                Toast.makeText(Login.this, "password : " +password, Toast.LENGTH_SHORT).show();

                Intent bt1 = new Intent(Login.this, Test.class);
                bt1.putExtra("id",id);
                bt1.putExtra("pwd", password);

                startActivity(bt1);


            }
        });

    }
}

