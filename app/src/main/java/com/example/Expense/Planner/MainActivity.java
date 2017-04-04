package com.example.expense.planner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   // public static final String LOGIN_URL = "http://192.168.43.40:1080/source/include/dblogin.php";

    public static  String KEY_USERNAME="user";
    public static  String KEY_PASSWORD="pass";


    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    private String user;
    private String pass;
   // Resources obj=new Resources();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        userLogin();
        editTextUsername.setText("");
        editTextPassword.setText("");
    }
    public void Register(View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
    public void forgot(View view){
        Intent intent = new Intent(this,Forgot.class);
        startActivity(intent);
    }



    private void userLogin() {
        user = editTextUsername.getText().toString().trim();
        pass = editTextPassword.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals(AppConfig.Credentials)){
                            Toast.makeText(MainActivity.this,AppConfig.Credentials,Toast.LENGTH_LONG).show();

                        }else if (response.equals(AppConfig.Valid)){
                            Toast.makeText(MainActivity.this,AppConfig.Valid,Toast.LENGTH_LONG).show();
                        }

                        else{

                            openProfile(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_USERNAME,user);
                map.put(KEY_PASSWORD,pass);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openProfile(String rec){
        String Rec =rec;
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("MyNeed", Rec);
        startActivity(intent);
    }


}