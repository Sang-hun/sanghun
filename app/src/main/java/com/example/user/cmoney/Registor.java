package com.example.user.cmoney;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class Registor extends AppCompatActivity {

    EditText id,onepwd,twopwd;
    Button check, Save;
    TextView idworong,pwdwrong,pwdok;
    String TrueorFalse;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registor);

        id = (EditText)findViewById(R.id.newid);
        onepwd = (EditText)findViewById(R.id.newpwd);
        twopwd = (EditText)findViewById(R.id.onemorpwd);
        idworong = (TextView)findViewById(R.id.noid);
        pwdwrong = (TextView)findViewById(R.id.nomatch);
        pwdok = (TextView)findViewById(R.id.okpwd);

        check = (Button)findViewById(R.id.idcheck);
        check.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if(id.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"입력하셔야죠",Toast.LENGTH_SHORT).show();
                }
                else{
                    new httpTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "check_id_get.php?id=" + id.getText().toString()," ");
                }
            }
        });

        Save = (Button)findViewById(R.id.save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (id.getText().toString().equals("")||onepwd.getText().toString().equals("")||twopwd.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"입력하셔야죠",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent bt1 = new Intent(Registor.this, Login.class);
                    startActivity(bt1);
                }
            }
        });

        twopwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (onepwd.getText().toString().equals(twopwd.getText().toString())){
                    pwdwrong.setText(new StringBuilder().append("비밀번호가 일치합니다."));
                    pwdwrong.setTextColor(Color.BLUE);
                    TrueorFalse = "true";
                } else {
                    pwdwrong.setText(new StringBuilder().append("비밀번호가 다릅니다."));
                    pwdwrong.setTextColor(Color.RED);
                    TrueorFalse = "false";
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private class httpTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... args) {

            String returnValue = "";
            HttpURLConnection conn = null;
            try {
                Log.e("!!!", "args[0] = " + args[0]);
                Log.e("!!!", "args[1] = " + args[1]);
                String urlString = "http://www.matescorp.com/soyu/" + args[0];
                Log.e("!!!", "urlString = " + urlString);
                URL url = new URL(urlString);

                // open connection
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);            // 입력스트림 사용여부
                conn.setDoOutput(false);            // 출력스트림 사용여부
                conn.setUseCaches(false);        // 캐시사용 여부
                conn.setReadTimeout(3000);        // 타임아웃 설정 ms단위
                conn.setRequestMethod("GET");
//                conn.setRequestMethod("POST"); // or POST

                // POST 값 전달 하기
//                StringBuffer params = new StringBuffer("");
////                params.append("name=" + URLEncoder.encode(name)); //한글일 경우 URL인코딩
//                params.append(args[1]);
//                PrintWriter output = new PrintWriter(conn.getOutputStream());
//                output.print(params.toString());
//                output.close();

                // Response받기
                StringBuffer sb = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                for (; ; ) {
                    String line = br.readLine();
                    if (line == null) break;
                    sb.append(line + "\n");
                }

                br.close();
                conn.disconnect();
                conn = null;

                returnValue = sb.toString();
            } catch (ConnectException e) {
                e.printStackTrace();
                return "ConnectException|" + args[0] + "|" + args[1];
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                return "SocketTimeoutException|" + args[0] + "|" + args[1];
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return returnValue;
        }

        protected void onPostExecute(String result) {
            result = result.trim();
            Log.e("!!!", "httpTask result = | " + result + " |");
            if (result.trim().equals("") || result.trim().equals("[]") || result.trim().equals("null")) {
                Log.e("!!!", "------");
                return;

            } else{
                try {
                    if(result.equals("success")){

                        idworong.setText(new StringBuilder().append("이미 등록된 아이디입니다."));
                        idworong.setTextColor(Color.RED);
                        TrueorFalse="false";
//                        String id1 = id.getText().toString();
//                        String password = onepwd.getText().toString();
//                        Intent bt1 = new Intent(Registor.this, Login.class);
//                        bt1.putExtra("id",id1);
//                        bt1.putExtra("pwd", password);
//                        startActivity(bt1);
                    }
                    else {
                        idworong.setText(new StringBuilder().append("가입 가능한 아이디 입니다."));
                        idworong.setTextColor(Color.BLUE);
                        TrueorFalse="true";

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
