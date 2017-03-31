package com.example.Expense.Planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    String here;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tv =(TextView)findViewById(R.id.focus);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
            here = extras.getString("MyNeed");
        tv.setText(here);
    }

    public void send(View view){
        int val=0;
        new Session(val);
    }
    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

    public void expenses(View view) {
        Intent i = new Intent(Profile.this,Expense.class);
        startActivity(i);
    }
    public void settlement(View view) {
        Intent i = new Intent(Profile.this,Settlement.class);
        startActivity(i);
    }
    public void report(View view) {
        Intent i = new Intent(this,DetailedReport.class);
        startActivity(i);
    }
    public void summary(View view) {
        Intent i = new Intent(Profile.this,Summary.class);
        startActivity(i);
    }
    public void ocrep(View view) {
        Intent i = new Intent(Profile.this,Ocrep.class);
        startActivity(i);
    }

}
