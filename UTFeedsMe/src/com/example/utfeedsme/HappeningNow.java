package com.example.utfeedsme;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/*
 * SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
				Date d;
				try {
					Log.d(TAG, "parsing: " + event_date.getText().toString());
					d = formatter.parse(event_date.getText().toString());
					gameScore.put("date", d);
					//Log.d(TAG, "SUCCESS!!!!");
				}
				catch (ParseException e) {
					e.printStackTrace();
				}
 */
public class HappeningNow extends Activity {

    private ParseQueryAdapter<ParseObject> mainAdapter;
    private CustomAdapter urgentTodosAdapter;
    private ListView listView;
    
    private static final String TAG = "HappeningNow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.happening_now);

        // Initialize main ParseQueryAdapter
        ParseQueryAdapter.QueryFactory<ParseObject> factory = 
        		new ParseQueryAdapter.QueryFactory<ParseObject>() {
            public ParseQuery<ParseObject> create() {
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("FoodEvent");
                SimpleDateFormat f = new SimpleDateFormat("MMM dd, yyyy");
                Date d = new Date();
                String rep = f.format(d);
              //  Log.d(TAG, rep);
                query.whereEqualTo("date", rep);
                SimpleDateFormat t = new SimpleDateFormat("HH:mm");
                query.whereLessThanOrEqualTo("start_time", t.format(d));
                Log.d(TAG, t.format(d));
                query.whereGreaterThanOrEqualTo("end_time", t.format(d));
                return query;
              }
            };
        //Log.d(TAG, "created factory");
        mainAdapter = new ParseQueryAdapter<ParseObject>(this, factory);
        //Log.d(TAG, "created adapter");
        mainAdapter.setTextKey("event");
        mainAdapter.setImageKey("Image");
        
        ParseQuery<ParseObject> q = ParseQuery.getQuery("FoodEvent");
        
        // get rid of outdated events
        Date d = new Date();
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        q.whereLessThan("end_time", f.format(d));
     
        q.findInBackground(new FindCallback<ParseObject>() {
        	public void done(List<ParseObject> objects, ParseException e) {
        		if (e == null) {
        			Log.d(TAG, "size: " + objects.size());
        			ParseObject.deleteAllInBackground(objects);
        		}
        		else {
        			e.printStackTrace();
        		}
        	}
        });
        

        // Initialize the subclass of ParseQueryAdapter
        urgentTodosAdapter = new CustomAdapter(this, CustomAdapter.ContextType.HAPPENING_NOW);

        // Initialize ListView and set initial view to mainAdapter
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(mainAdapter);
        //mainAdapter.loadObjects();

        // Initialize toggle button
        Button toggleButton = (Button) findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listView.getAdapter() == mainAdapter) {
                    listView.setAdapter(urgentTodosAdapter);
          //          urgentTodosAdapter.loadObjects();
                } else {
                    listView.setAdapter(mainAdapter);
            //        mainAdapter.loadObjects();
                }
            }

        });
    }

}