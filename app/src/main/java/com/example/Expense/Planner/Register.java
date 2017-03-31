package com.example.Expense.Planner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener {
        public static  String KEY_USERNAME = "name";
        public static  String KEY_PASSWORD = "pass";
	    public static  String KEY_Familyname = "fname";
	    public static  String KEY_Email = "email";
	    public static  String KEY_Gender = "gen";
		public static  String KEY_DOB = "dob";

		private EditText editTextName;
		private EditText editTextDob;
		private EditText editTextfname;
		private EditText editTextgen;
		private EditText editTextPassword;
	    private EditText editTextEmail;
		DatePickerDialog datePickerDialog;

		private String name;
		private String pass;
		private String fname;
		private String email;
		private String gen;
		private String dob;


		private Button Reg;
		Resources obj=new Resources();

		@Override
		protected void onCreate(Bundle savedInstanceState) {
		      super.onCreate(savedInstanceState);
		      setContentView(R.layout.activity_register);
		      editTextDob = (EditText) findViewById(R.id.eddob);
		      editTextDob.setOnClickListener(new View.OnClickListener() {
				  @Override
				 public void onClick(View v) {
		      final Calendar c = Calendar.getInstance();
		      int mYear = c.get(Calendar.YEAR); // current year
			  int mMonth = c.get(Calendar.MONTH); // current month
			  int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
			  datePickerDialog = new DatePickerDialog(Register.this,new DatePickerDialog.OnDateSetListener() {
																															                            @Override
              public void onDateSet(DatePicker view, int year,
              int monthOfYear, int dayOfMonth) {
                  // set day of month , month and year value in the edit text
                  editTextDob.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);}}, mYear, mMonth, mDay);
				  datePickerDialog.show();
				 }
		      });
			  editTextName = (EditText) findViewById(R.id.edname);
			  editTextPassword = (EditText) findViewById(R.id.edpassword);
			  editTextEmail = (EditText) findViewById(R.id.edemail);
			  editTextfname=(EditText)findViewById(R.id.edfname);
			  editTextgen=(EditText)findViewById(R.id.edgen);
			  Reg = (Button) findViewById(R.id.btregister);
			  Reg.setOnClickListener(this);
		}

	public void log(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	@Override
	public void onClick(View v) {
		registerUser();
		editTextName.setText("");
		editTextPassword.setText("");
		editTextEmail.setText("");
		editTextDob.setText("");
		editTextfname.setText("");
		editTextgen.setText("");
	}
	private void registerUser() {
		name = editTextName.getText().toString().trim();
		pass = editTextPassword.getText().toString().trim();
		email = editTextEmail.getText().toString().trim();
		gen = editTextgen.getText().toString().trim();
		fname = editTextfname.getText().toString().trim();
		dob = editTextDob.getText().toString().trim();
		StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.REGISTER_URL,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						if (response.equals(obj.Exists)) {
							Toast.makeText(Register.this,obj.Exists, Toast.LENGTH_LONG).show();
						} else if (response.equals(obj.Valid)) {
							Toast.makeText(Register.this,obj.Valid, Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(Register.this,obj.register, Toast.LENGTH_LONG).show();
						}
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(Register.this, error.toString(), Toast.LENGTH_LONG).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put(KEY_USERNAME, name);
				map.put(KEY_PASSWORD, pass);
				map.put(KEY_Familyname, fname);
				map.put(KEY_Email, email);
				map.put(KEY_Gender, gen);
				map.put(KEY_DOB, dob);
				return map;
			}
		};
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);
	}
}

