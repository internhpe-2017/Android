package com.example.expense.planner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




public class Ocrep extends AppCompatActivity implements Spinner.OnItemSelectedListener{


    private Spinner spinner;


    private ArrayList<String> students;


    private JSONArray result;


    private TextView textViewParticulars;
    private TextView textViewAmount;
    private TextView textViewDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        students = new ArrayList<String>();


        spinner = (Spinner) findViewById(R.id.spinner);


        spinner.setOnItemSelectedListener(this);


        textViewParticulars = (TextView) findViewById(R.id.textViewParticulars);
        textViewAmount = (TextView) findViewById(R.id.textViewAmount);
        textViewDate = (TextView) findViewById(R.id.textViewDate);


        // for (int i = 0;i<students.lenth(); i++) {
        getData();
        // }
    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(AppConfig.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {

                            j = new JSONObject(response);


                            result = j.getJSONArray(AppConfig.JSON_ARRAY);


                            getStudents(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);


        requestQueue.add(stringRequest);
    }

    private void getStudents(JSONArray j){
        String name="";

        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

//                for (int i = 0; i < jsonArray.length(); i++) {
//                    if(!Clist.contains(jsonArray.getJSONObject(i).getString("standard"))){
//                        cList.add();
//                    }
//                }
                if(!students.contains(json.getString(AppConfig.TAG_OCCASION))) {
                    students.add(json.getString(AppConfig.TAG_OCCASION));
                }
                //Adding the name of the student to array list
                //         name=students.getString(Config.TAG_PARTICULARS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(Ocrep.this, android.R.layout.simple_spinner_dropdown_item, students));
    }

    //Method to get student name of a particular position
    private String getName(int position){
        String name="";
        for(int i=0;i<result.length();i++) {

            try {
                //Getting object of given index
                JSONObject json = result.getJSONObject(position);

                //Fetching name from that object
                name = json.getString(AppConfig.TAG_PARTICULARS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Returning the name
        }
        return name;

    }

    //Doing the same with this method as we did with getName()
    private String getCourse(int position){
        String course="";
        for(int i=0;i<result.length();i++) {
            try {
                JSONObject json = result.getJSONObject(position);
                course = json.getString(AppConfig.TAG_AMOUNT);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return course;
    }

    //Doing the same with this method as we did with getName()
    private String getSession(int position){
        String session="";
        for(int i=0;i<result.length();i++) {
            try {
                JSONObject json = result.getJSONObject(position);
                session = json.getString(AppConfig.TAG_DATE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return session;
    }


    //this method will execute when we pic an item from the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Setting the values to textviews for a selected item
        // for (int i = 0; i<result.length(); i++) {
        textViewParticulars.setText(getName(position));
        textViewAmount.setText(getCourse(position));
        textViewDate.setText(getSession(position));
        //    }
    }
    //When no item is selected this method would execute
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        textViewParticulars.setText("");
        textViewAmount.setText("");
        textViewDate.setText("");
    }
}