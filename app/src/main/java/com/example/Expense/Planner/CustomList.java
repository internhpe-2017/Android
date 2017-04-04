package com.example.expense.planner;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by hmna on 3/24/2017.
 */

public class CustomList extends ArrayAdapter<String> {
    private String[] ids;
    private String[] occasion;
    private String[] particulars;
    private String[] amount;
    private String[] date;
    private String[] to;
    private Activity context;

    public CustomList(Activity context, String[] ids, String[] occasion, String[] particulars, String[] amount, String[] date, String[] to)
    {
        super(context, R.layout.list_view_layout, ids);
        this.context = context;
        this.ids = ids;
        this.occasion = occasion;
        this.particulars = particulars;
        this.amount = amount;
        this.date = date;
        this.to = to;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewOccasion = (TextView) listViewItem.findViewById(R.id.textViewOccasion);
        TextView textViewParticulars = (TextView) listViewItem.findViewById(R.id.textViewParticulars);
        TextView textViewAmount = (TextView) listViewItem.findViewById(R.id.textViewAmount);
        TextView textViewDate = (TextView) listViewItem.findViewById(R.id.textViewDate);
        TextView textViewTo = (TextView) listViewItem.findViewById(R.id.textViewTo);

        textViewId.setText(ids[position]);
        textViewOccasion.setText(occasion[position]);
        textViewParticulars.setText(particulars[position]);
        textViewAmount.setText("Rs."+ amount[position]);
        textViewDate.setText(date[position]);
        textViewTo.setText("spent to:"+to[position]);
        return listViewItem;
    }
}
