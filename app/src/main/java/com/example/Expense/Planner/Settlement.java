package com.example.Expense.Planner;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Settlement extends AppCompatActivity implements View.OnClickListener {

    public static String KEY_OWE = "Owe";
    public static String KEY_OCCASION = "Occasion";
    public static String KEY_AMOUNT = "Amount";
    public static String KEY_STATUSBUTTON = "Statusbutton";

    private EditText owe;
    private EditText occasion;
    private EditText amount;
    private RadioGroup statusgroup;
    private RadioButton statusbutton;
    private Button buttonadd;

    private String Owe;
    private String Occasion;
    private String Amount;
    private String Statusbutton;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settlement);
        owe = (EditText) findViewById(R.id.owe);
        occasion = (EditText) findViewById(R.id.occasion);
        amount = (EditText) findViewById(R.id.amount);
        statusgroup=(RadioGroup)findViewById(R.id.radioGroup);
        buttonadd = (Button) findViewById(R.id.buttonadd);

        buttonadd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int selectedId=statusgroup.getCheckedRadioButtonId();
        statusbutton=(RadioButton)findViewById(selectedId);
        if(v == buttonadd){
            addsettlement();
            owe.setText("");
            occasion.setText("");
            amount.setText("");
            statusbutton.setText("");
        }
    }

    private void addsettlement() {

        Owe = owe.getText().toString().trim().toLowerCase();
        Occasion = occasion.getText().toString().trim().toLowerCase();
        Amount = amount.getText().toString().trim();
        Statusbutton = String.valueOf(statusbutton.getText());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.SETTLEMENT_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        if (response.equals(obj.Valid)) {
                            Toast.makeText(Settlement.this,obj.Valid, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Settlement.this,obj.Sucess, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Settlement.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_OWE, Owe);
                map.put(KEY_OCCASION, Occasion);
                map.put(KEY_AMOUNT, Amount);
                map.put(KEY_STATUSBUTTON, Statusbutton);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    Resources obj=new Resources();
}
