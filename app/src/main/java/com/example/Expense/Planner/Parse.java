package com.example.Expense.Planner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hmna on 3/31/2017.
 */

public class Parse {
    public static String[] ids;
    public static String[] occasion;
    public static String[] particulars;
    public static String[] amount;
    //  public static String[] date;
    public static String[] to;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_ID = "id";
    public static final String KEY_OCCASION = "occasion";
    public static final String KEY_PARTICULARS = "particulars";
    public static final String KEY_AMOUNT = "amount";
    //   public static final String KEY_DATE = "date";
    public static final String KEY_TO = "to";

    private JSONArray users = null;
    int qtySum=0;
    int qtyNum;
    private String json;

    public Parse(String json){
        this.json = json;
    }

    protected void parse(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            ids = new String[users.length()];
            occasion = new String[users.length()];
            particulars = new String[users.length()];
            amount = new String[users.length()];
            //        date = new String[users.length()];
            to = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                occasion[i] = jo.getString(KEY_OCCASION);
                particulars[i] = jo.getString(KEY_PARTICULARS);
                amount[i] = jo.getString(KEY_AMOUNT);
                //         date[i] = jo.getString(KEY_DATE);
                to[i] = jo.getString(KEY_TO);
                qtyNum = Integer.parseInt(jo.getString(KEY_AMOUNT));
                qtySum += qtyNum;
            }
            // textTotalitems.setText(String.valueOf(qtySum));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
