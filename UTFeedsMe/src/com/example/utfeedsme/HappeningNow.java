package com.example.utfeedsme;

/*
 * Database code derived from Lars Vogel
 * http://www.vogella.com/tutorials/AndroidSQLite/article.html
 */

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.widget.Button;

public class HappeningNow extends ListActivity {
	
//	protected RecordsDataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.happening_now);
		
//        dataSource = new RecordsDataSource(this);
//        dataSource.open();
//		
//		List<Record> values = dataSource.getTimeRecords();
//
//	    // use the SimpleCursorAdapter to show the
//	    // elements in a ListView
//	    ArrayAdapter<Record> adapter = new ArrayAdapter<Record>(this,
//	        android.R.layout.simple_list_item_1, values);
//	    setListAdapter(adapter);
	}
}
