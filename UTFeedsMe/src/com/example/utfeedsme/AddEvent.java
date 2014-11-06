package com.example.utfeedsme;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class AddEvent extends ListActivity {
	
	private final int MINS_PER_DAY = 1440;
	protected RecordsDataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_event);
		
        dataSource = new RecordsDataSource(this);
        dataSource.open();
        
        List<Record> values = dataSource.getAllRecords();

	    // use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Record> adapter = new ArrayAdapter<Record>(this,
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
		
	    adapter = (ArrayAdapter<Record>) getListAdapter();
	    Record record = null;
		int startTime = new Random().nextInt(MINS_PER_DAY);
		int endTime = new Random().nextInt((MINS_PER_DAY) - startTime) + startTime;
		String[] locations = new String[] {"PCL", "GDC", "JES", "ENS", "SAC", "FAC", "CBA"};
		int randLocIndex = new Random().nextInt(7);
		String[] foods = new String[] {"American", "Mexican", "Chinese", "Mediterranean", "Thai", "Japanese"};
		int randFoodIndex = new Random().nextInt(6);
	    // save the new comment to the database
	    record = dataSource.createRecord(startTime, endTime, locations[randLocIndex], foods[randFoodIndex]);
	    adapter.add(record);
	    
	}
}
