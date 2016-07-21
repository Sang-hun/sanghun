package com.example.user.cmoney;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;



public class Login extends Activity {
    private Context CONTEXT;
     EditText name;
     EditText pwd;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        CONTEXT = this;
        Button login;

        name = (EditText)findViewById(R.id.name);
        pwd = (EditText)findViewById(R.id.pwd);
//        OnCreate 밖에 선언해야 인식이 가능하다(키보드 보이기)
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInputFromInputMethod (name.getApplicationWindowToken(),InputMethodManager.SHOW_FORCED);

        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                 String id = name.getText().toString();
                 String password = pwd.getText().toString();

                if("".equals(id) || "".equals(password)){
                    Toast.makeText(getApplicationContext(), "아이디랑 비밀번호 확인하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                    Intent bt1 = new Intent(Login.this, List.class);
                    startActivity(bt1);

//                bt1.putExtra("id",id);
//                bt1.putExtra("pwd", password);
//
//                startActivity(bt1);
//                if(id.equals("123") && password.equals("000")) {
//                    new httpTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "login_do_get.php?id="+id+"&pwd="+password, "");
//
//
//                }
//                else {
//                    new httpTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "login_do_get.php?id="+id+"&pwd="+password, "");
//
//
//                }


            }
        });

    }


//    private class httpTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... args) {
//
//            String returnValue = "";
//            HttpURLConnection conn = null;
//            try {
//                Log.e("!!!", "args[0] = " + args[0]);
//                Log.e("!!!", "args[1] = " + args[1]);
//                String urlString = "http://www.matescorp.com/soyu/" + args[0];
//                Log.e("!!!", "urlString = " + urlString);
//                URL url = new URL(urlString);
//
//                // open connection
//                conn = (HttpURLConnection) url.openConnection();
//                conn.setDoInput(true);            // 입력스트림 사용여부
//                conn.setDoOutput(false);            // 출력스트림 사용여부
//                conn.setUseCaches(false);        // 캐시사용 여부
//                conn.setReadTimeout(3000);        // 타임아웃 설정 ms단위
//                conn.setRequestMethod("GET");
////                conn.setRequestMethod("POST"); // or POST
//
//                // POST 값 전달 하기
////                StringBuffer params = new StringBuffer("");
//////                params.append("name=" + URLEncoder.encode(name)); //한글일 경우 URL인코딩
////                params.append(args[1]);
////                PrintWriter output = new PrintWriter(conn.getOutputStream());
////                output.print(params.toString());
////                output.close();
//
//                // Response받기
//                StringBuffer sb = new StringBuffer();
//                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//                for (; ; ) {
//                    String line = br.readLine();
//                    if (line == null) break;
//                    sb.append(line + "\n");
//                }
//
//                br.close();
//                conn.disconnect();
//                br = null;
//                conn = null;
//
//                returnValue = sb.toString();
//            } catch (ConnectException e) {
//                e.printStackTrace();
//                return "ConnectException|" + args[0] + "|" + args[1];
//            } catch (SocketTimeoutException e) {
//                e.printStackTrace();
//                return "SocketTimeoutException|" + args[0] + "|" + args[1];
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (conn != null) {
//                    conn.disconnect();
//                }
//            }
//            return returnValue;
//        }
//
//        protected void onPostExecute(String result) {
//            result = result.trim();
//            Log.e("!!!", "httpTask result = | " + result + " |");
//            if (result.trim().equals("") || result.trim().equals("[]") || result.trim().equals("null")) {
//                Log.e("!!!", "------");
//                return;
//
//            } else{
//                try {
//                    if(result.equals("success")){
//                        String id = name.getText().toString();
//                        String password = pwd.getText().toString();
//                        Intent bt1 = new Intent(Login.this, Test.class);
//                        bt1.putExtra("id",id);
//                        bt1.putExtra("pwd", password);
//                        startActivity(bt1);
//                    }
//                    Toast.makeText(CONTEXT, result, Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}

