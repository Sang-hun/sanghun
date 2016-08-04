package com.example.user.cmoney;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Please extends AppCompatActivity implements onNetworkResponseListener {

    EditText Use;
    EditText UseMoney;
    EditText UseDate;
    Spinner sp;
    AccountTitleSpinnerList spinnerList;

    static final int DATE_DIALOG_ID = 0;

    public int year, month, day;
    private int mYear, mMonth, mDay;

    public Please() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    }


    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.enableDefaults();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.please);


        getAccountList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.please);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        ImageView Save;

        Use = (EditText)findViewById(R.id.use);
        UseMoney = (EditText)findViewById(R.id.useMoney);
        UseDate = (EditText)findViewById(R.id.useDate);

        UseDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        Save = (ImageView)findViewById(R.id.save);
        Save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String use = Use.getText().toString();
                String usemoney = UseMoney.getText().toString();
                String usedate = UseDate.getText().toString();

                try{
                    Integer.parseInt(usemoney);
                    if ("".equals(use)||"".equals(usemoney)||"".equals(usedate)){

                        Toast.makeText(getApplicationContext(), "사용처, 사용금액, 사용일자를 필수로 입력하세요", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent bt1 = new Intent(Please.this, List.class);
                    startActivity(bt1);
                }

                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "사용금액을 적절하게 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int yearSelected,
                              int monthOfYear, int dayOfMonth) {
            year = yearSelected;
            month = monthOfYear + 1;
            day = dayOfMonth;
            Toast.makeText(getApplicationContext(), "날짜: "+year+"-"+month+"-"+day,
                    Toast.LENGTH_SHORT).show();
            UseDate.setText(year + " - " + month + " - " + day);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
                        mDay);
        }
        return null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return (super.onOptionsItemSelected(menuItem));
    }
    //요청부분
    public void getAccountList(){
        JSONObject req_data = new JSONObject();
        try {
            req_data.put("USER_ID", "test_user1");

            CommNetwork commNetwork = new CommNetwork(this, this);
            commNetwork.requestToServer("ACCOUNT_L001",req_data);
        } catch (Exception e) {
            Error.AlertException(this, "오류발생", e);

        }
    }

    public void onSuccess(String api_key, JSONObject response) {
        Toast.makeText(this, "요청성공",Toast.LENGTH_SHORT).show();

        try {
            JSONArray array = response.getJSONArray("REC");
             spinnerList = new AccountTitleSpinnerList(array);
             ArrayAdapter<String> adapter = new ArrayAdapter<>(Please.this, android.R.layout.simple_spinner_item, spinnerList.getArrayList());
             adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
             sp = (Spinner) findViewById(R.id.spinner1);
             sp.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFailure(String api_key, String error_cd, String error_msg) {
        Toast.makeText(this, "요청실패",Toast.LENGTH_SHORT).show();
    }
}
