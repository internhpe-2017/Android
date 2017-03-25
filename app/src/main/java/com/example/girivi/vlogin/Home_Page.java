package com.example.girivi.vlogin;

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
        Intent i = new Intent(this,expense.class);
        startActivity(i);
    }
    public void settlement(View view) {
        Intent i = new Intent(this,settlement.class);
        startActivity(i);
    }
    public void report(View view) {
        Intent i = new Intent(this,report.class);
        startActivity(i);
    }
    public void summary(View view) {
        Intent i = new Intent(this,summary.class);
        startActivity(i);
    }
    public void ocrep(View view) {
        Intent i = new Intent(this,ocrep.class);
        startActivity(i);
    }
}