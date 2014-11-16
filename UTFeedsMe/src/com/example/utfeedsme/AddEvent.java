package com.example.utfeedsme;

import java.util.List;
import java.util.Random;

import com.parse.ParseObject;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class AddEvent extends Activity {
	
//	private final int MINS_PER_DAY = 1440;
//	protected RecordsDataSource dataSource;
	Button add_event;
	EditText event_name, event_where, event_time, event_food;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("addevent", "entered oncreate for addevent");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_event);
		event_name = (EditText) findViewById(R.id.add_event_name);
		event_where = (EditText) findViewById(R.id.add_event_where);
		event_time = (EditText) findViewById(R.id.add_event_time);
		event_food = (EditText) findViewById(R.id.add_event_food);
		Button add_event = (Button) findViewById(R.id.add_event_btn);
		add_event.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.v("addevent", "event: " + event_name.getText().toString()+
						", " + event_where.getText().toString() + 
						", " + event_time.getText().toString() +
						", " + event_food.getText().toString());
				ParseObject gameScore = new ParseObject("FoodEvent");
				gameScore.put("event", event_name.getText().toString());
				gameScore.put("time", event_time.getText().toString());
				gameScore.put("where", event_where.getText().toString());
				gameScore.put("food", event_food.getText().toString());
				gameScore.saveInBackground();
			}
		});

//        dataSource = new RecordsDataSource(this);
//        dataSource.open();
//        
//        List<Record> values = dataSource.getAllRecords();
//
//	    // use the SimpleCursorAdapter to show the
//	    // elements in a ListView
//	    ArrayAdapter<Record> adapter = new ArrayAdapter<Record>(this,
//	        android.R.layout.simple_list_item_1, values);
//	    setListAdapter(adapter);
//		
//	    adapter = (ArrayAdapter<Record>) getListAdapter();
//	    Record record = null;
//		int startTime = new Random().nextInt(MINS_PER_DAY);
//		int endTime = new Random().nextInt((MINS_PER_DAY) - startTime) + startTime;
//		String[] locations = new String[] {"PCL", "GDC", "JES", "ENS", "SAC", "FAC", "CBA"};
//		int randLocIndex = new Random().nextInt(7);
//		String[] foods = new String[] {"American", "Mexican", "Chinese", "Mediterranean", "Thai", "Japanese"};
//		int randFoodIndex = new Random().nextInt(6);
//	    // save the new comment to the database
//	    record = dataSource.createRecord(startTime, endTime, locations[randLocIndex], foods[randFoodIndex]);
//	    adapter.add(record);
	    
	}
}
