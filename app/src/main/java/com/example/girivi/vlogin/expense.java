package com.example.girivi.vlogin;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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

public class Expense extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_USERNAME = "Username";
    public static final String KEY_PARTICULAR = "Particular";
    public static final String KEY_DATE = "Date";
    public static final String KEY_OCCASION = "Occasion";
    public static final String KEY_AMOUNT = "Amount";

    private EditText username;
    private EditText particular;
    private EditText date;
    private EditText occasion;
    private EditText amount;
    private Button buttonadd;
    DatePickerDialog datePickerDialog;

    private String Username;
    private String Particular;
    private String Date;
    private String Occasion;
    private String Amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        username = (EditText) findViewById(R.id.username);
        particular = (EditText) findViewById(R.id.particular);
        date = (EditText) findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(Expense.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        occasion = (EditText) findViewById(R.id.occasion);
        amount = (EditText) findViewById(R.id.amount);
        buttonadd = (Button) findViewById(R.id.buttonadd);

        buttonadd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonadd){
            addexpenses();
            username.setText("");
            particular.setText("");
            date.setText("");
            occasion.setText("");
            amount.setText("");
        }
    }

    private void addexpenses() {
        Username = username.getText().toString().trim().toLowerCase();
        Particular = particular.getText().toString().trim().toLowerCase();
        Date = date.getText().toString().trim().toLowerCase();
        Occasion = occasion.getText().toString().trim().toLowerCase();
        Amount = amount.getText().toString().trim().toLowerCase();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.ADD_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("please fill all values")) {
                            Toast.makeText(Expense.this,"Please Enter all fields", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Expense.this,"Successfully Added !!!", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Expense.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_USERNAME, Username);
                map.put(KEY_PARTICULAR, Particular);
                map.put(KEY_DATE, Date);
                map.put(KEY_OCCASION, Occasion);
                map.put(KEY_AMOUNT, Amount);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}