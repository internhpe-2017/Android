package com.example.expense.planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Reset extends AppCompatActivity implements View.OnClickListener {
    public static  String KEY_EMAIL="email";
    public static  String KEY_PASS="pass";
    private EditText enter;
    private EditText confirm;
    private Button submit;
    private String Enter;
    private String Pass;
    Resources obj=new Resources();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        enter = (EditText) findViewById(R.id.etentered);
        confirm = (EditText) findViewById(R.id.etconfirm);
        submit= (Button) findViewById(R.id.reset);
        submit.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Enter = enter.getText().toString().trim();
        Pass = confirm.getText().toString().trim();
        if(Enter.equals("")||Pass.equals("")){
            Toast.makeText(getApplicationContext(),AppConfig.Valid,Toast.LENGTH_LONG).show();
        }
        else{
            if(Enter.equals(Pass)){
                reset();
                Intent i= new Intent(this,MainActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(getApplicationContext(),obj.compare,Toast.LENGTH_LONG).show();
            }
        }
    }
    private void reset() {
        Enter = enter.getText().toString().trim();
        Pass = confirm.getText().toString().trim();
        Bundle extras = getIntent().getExtras();
        final String userEmail= extras.getString("KEY");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.Reset_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Reset.this, response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Reset.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_EMAIL,userEmail);
                map.put(KEY_PASS,Pass);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
