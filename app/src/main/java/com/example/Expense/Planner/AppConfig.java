package com.example.expense.planner;

/**
 * Created by girivi on 3/31/2017.
 */

public interface AppConfig  {

     String LOGIN_URL = "http://triphpe.16mb.com/nlog.php";
     String REGISTER_URL = "http://triphpe.16mb.com/vreg.php";
     String Forgot_URL = "http://triphpe.16mb.com/forgotpass.php";
     String Reset_URL= "http://triphpe.16mb.com/reset.php";
     String JSON_URL = "http://www.triphpe.890m.com/getData.php";
     String ADD_URL = "http://triphpe.16mb.com/expense.php";
     String JSON_URL1 = "http://www.triphpe.890m.com/getData1.php";
     String SETTLEMENT_URL = "http://triphpe.16mb.com/settlement.php";
     String DATA_URL = "http://triphpe.890m.com/getData.php";



     //Tags used in the JSON String
     public static final String TAG_OCCASION = "occasion";
     public static final String TAG_PARTICULARS = "particulars";
     public static final String TAG_AMOUNT = "amount";
     public static final String TAG_DATE = "date";

     //JSON array name
     public static final String JSON_ARRAY = "result";

     //Volley Response
     String Credentials="Sorry, Invalid Credentials";
     String Valid="please fill all Fields";
     String Exists="username or email already exist";
     String register="Succesfully Registered";
}
