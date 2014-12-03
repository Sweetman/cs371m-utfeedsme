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

/*
 * ParseObject gameScore = new ParseObject("FoodEvent");
				gameScore.put("event", event_name.getText().toString());
				gameScore.put("start_time", event_start_time.getText().toString());
				gameScore.put("end_time", event_end_time.getText().toString());
				gameScore.put("date", event_date.getText().toString());
				gameScore.put("where", event_where.getText().toString());
				gameScore.put("food", event_food.getText().toString());
				gameScore.saveInBackground();
 */

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
                		SimpleDateFormat f = new SimpleDateFormat("MMM dd, yyyy");
                		Date d = new Date();
                        String rep = f.format(d);
                      //  Log.d(TAG, rep);
                        query.whereEqualTo("date", rep);
                        SimpleDateFormat t = new SimpleDateFormat("HH:mm");
                        query.whereLessThanOrEqualTo("start_time", t.format(d));
                        Log.d(TAG, t.format(d));
                        query.whereGreaterThanOrEqualTo("end_time", t.format(d));
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
/*    
    public void deleteItems() {
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("FoodEvent");
    	query.findInBackground(new FindCallback<ParseObject>() {
		     public void done(List<ParseObject> objects, ParseException e) {
		         if (e == null) {
			        	 try {
			        		 ParseObject.deleteAll(objects);
			        	 }
			        	 catch (ParseException ex) {
			        		 ex.printStackTrace();
			        	 }
			         } else {
			             e.printStackTrace();
			         }
			     }
			 });
    }
*/    

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(ParseObject object, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.urgent_item, null);
        }

        super.getItemView(object, v, parent);

        /*
        // Add and download the image
        ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile imageFile = object.getParseFile("Image");
        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
        }
		*/

        // Add the title view
        TextView foodTextView = (TextView) v.findViewById(R.id.food);
        foodTextView.setText("Food: " + object.getString("food"));
        
        TextView locationTextView = (TextView) v.findViewById(R.id.location);
        locationTextView.setText("Location: " + object.getString("where"));
        
        TextView dateTextView = (TextView) v.findViewById(R.id.date);
        /*
        Date d = object.getDate("date");
        if (d == null) {
        	Log.d(TAG, "jeez...");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        */
        
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
