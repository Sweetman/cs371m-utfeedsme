package com.example.utfeedsme;

/*
 * Database code derived from Lars Vogel
 * http://www.vogella.com/tutorials/AndroidSQLite/article.html
 */

import android.os.Bundle;

import java.util.List;
import java.util.Random;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;

public class AllEvents extends ListActivity {
	
	protected RecordsDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_events);
		ParseQueryAdapter<ParseObject> mainAdapter = new ParseQueryAdapter<ParseObject>(this, "FoodEvent");
		mainAdapter.setTextKey("event");
		System.out.println("hello");
		setListAdapter(mainAdapter);
	}
}
