package com.example.utfeedsme;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class CustomAdapter extends ParseQueryAdapter<ParseObject> {
	
	private static final String TAG = "CustomAdapter";
	
	public enum ContextType {HAPPENING_NOW, NEAR_YOU, ALL_EVENTS};

    public CustomAdapter(Context context, final ContextType c) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("FoodEvent");
                switch (c) {
                	case HAPPENING_NOW:
                		break;
                	case NEAR_YOU:
                		
                		break;
                	case ALL_EVENTS:
                		break;
               		}
                return query;
                }	
        });
    }


    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.urgent_item, null);
        }

        super.getItemView(object, v, parent);

        TextView foodTextView = (TextView) v.findViewById(R.id.food);
        foodTextView.setText("Food: " + object.getString("food"));
        
        TextView locationTextView = (TextView) v.findViewById(R.id.location);
        locationTextView.setText("Location: " + object.getString("where"));
        
        TextView dateTextView = (TextView) v.findViewById(R.id.date);
        
        dateTextView.setText("Date: " + object.getString("date"));
        
        TextView startTextView = (TextView) v.findViewById(R.id.start_time);
        startTextView.setText("Starts at: " + parseTime(object.getString("start_time")));

        // Add a reminder of how long this item has been outstanding
        TextView endTextView = (TextView) v.findViewById(R.id.end_time);
        endTextView.setText("Ends at: " + parseTime(object.getString("end_time")));
        return v;
    }
    
    public String parseTime(String time) {
    	StringBuilder result = new StringBuilder();
    	Log.d(TAG, "time: " + time);
    	Scanner s = new Scanner(time);
        s.useDelimiter(":");
        Log.d(TAG, "well? " + s.hasNext());
        String hourRep = s.next();
        int hourOfDay = Integer.parseInt(hourRep);
        int adjustedHour = (hourOfDay > 12) ? (hourOfDay - 12) : hourOfDay;
		  if (hourOfDay == 0) { // 12:00 am
			  adjustedHour = 12;
		}
		int minute = Integer.parseInt(s.next());  
		String minRep = (minute < 10) ? "0" + minute : Integer.toString(minute);  
		String amPm = (hourOfDay < 12) ? "AM" : "PM";
		return result.append(adjustedHour).append(":" + minRep).append(" " + amPm).toString();
    }

}
