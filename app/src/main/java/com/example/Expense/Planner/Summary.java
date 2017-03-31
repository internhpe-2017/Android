package com.example.Expense.Planner;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Summary extends AppCompatActivity implements View.OnClickListener {



    private Button buttonGet;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void sendRequest(){
        final ProgressDialog loading = ProgressDialog.show(this,"Loading Data", "Please wait...",false,false);
        StringRequest stringRequest = new StringRequest(AppConfig.JSON_URL1,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {
                        showJSON(response);
                        loading.dismiss();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Summary.this,error.getMessage(),Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        Parse pj = new Parse(json);
        pj.parse();
        SummaryList cl = new SummaryList(this,Parse.ids,Parse.occasion,Parse.particulars,Parse.amount,Parse.to);
        listView.setAdapter(cl);
    }

    @Override
    public void onClick(View v) {
        sendRequest();
    }
}
