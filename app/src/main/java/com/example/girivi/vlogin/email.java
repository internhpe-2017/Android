package com.example.girivi.vlogin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class email  extends AppCompatActivity{

    public class inner extends AsyncTask<String, Void, String> {


        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... arg0) {


            try {
                String username = (String) arg0[0];
                String password = (String) arg0[1];

                String link = Appconfig.Forgot_URL;
                String data = URLEncoder.encode("email", "UTF-8") + "=" +
                        URLEncoder.encode(username, "UTF-8");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }

                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }


        @Override
        protected void onPostExecute(String result) {

        }
    }
}

