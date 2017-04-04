package com.example.expense.planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
    }

    public void expenses(View view) {
        Intent i = new Intent(this,Expense.class);
        startActivity(i);
    }
    public void settlement(View view) {
        Intent i = new Intent(this,Settlement.class);
        startActivity(i);
    }
    public void report(View view) {
        Intent i = new Intent(this,DetailedReport.class);
        startActivity(i);
    }
    public void summary(View view) {
        Intent i = new Intent(this,Summary.class);
        startActivity(i);
    }
    public void ocrep(View view) {
        Intent i = new Intent(this,Ocrep.class);
        startActivity(i);
    }
}