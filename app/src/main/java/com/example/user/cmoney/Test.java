package com.example.user.cmoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Test extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        TextView id;
        TextView pwd;

        id = (TextView)findViewById(R.id.id);

        Intent in = getIntent();
        String idd = in.getExtras().getString("id");
        String pwdd = in.getExtras().getString("pwd");

        id.setText("ID : "+ idd +"     " +pwdd);

    }
}
