package com.inerun.dropinsta.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.inerun.dropinsta.data.StatusData;

import java.util.ArrayList;

/**
 * Created by vineet on 8/24/2016.
 */
public class StatusSpinnerAdapter extends ArrayAdapter<StatusData> {


    private Context context;

    private ArrayList<StatusData> statusDataArrayList;

    public StatusSpinnerAdapter(Context context, int textViewResourceId, ArrayList<StatusData> statusDataArrayList) {
        super(context, textViewResourceId, statusDataArrayList);
        this.context = context;
        this.statusDataArrayList = statusDataArrayList;
    }

    public int getCount() {
        return statusDataArrayList.size() - 1;
    }

    public StatusData getItem(int position) {
        return statusDataArrayList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = super.getView(position, convertView, parent);
        if (position == getCount()) {
            ((TextView) v.findViewById(android.R.id.text1)).setText("");
            ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount()).getLable()); //"Hint to be displayed"

        } else {

            ((TextView) v.findViewById(android.R.id.text1)).setText(statusDataArrayList.get(position).getLable());
        }

        ((TextView) v.findViewById(android.R.id.text1)).setTextSize(18);
        return v;


    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View v = super.getView(position, convertView, parent);

        ((TextView) v.findViewById(android.R.id.text1)).setTextSize(18);
        ((TextView) v.findViewById(android.R.id.text1)).setText(statusDataArrayList.get(position).getLable());

        return v;

//            TextView label = new TextView(context);
//            label.setTextSize(18);
//            label.setText(countryDataArrayList.get(position).getLable());
//            return label;

    }


}
