package com.example.utfeedsme;

import java.text.SimpleDateFormat;
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

public class AllEvents extends Activity {

    private ParseQueryAdapter<ParseObject> mainAdapter;
    private CustomAdapter urgentTodosAdapter;
    private ListView listView;
    
    private static final String TAG = "AllEvents";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_events);
        
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
        
        /*
        q.countInBackground(new CountCallback() {
        	public void done(int count, ParseException e) {
        		if (e == null) {
        			Log.d(TAG, "counted " + count + " items");
        		}
        		else {
        			e.printStackTrace();
        		}
        	}
        });
*/
        // Initialize the subclass of ParseQueryAdapter
        urgentTodosAdapter = new CustomAdapter(this, CustomAdapter.ContextType.ALL_EVENTS);

        // Initialize ListView and set initial view to mainAdapter
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(urgentTodosAdapter);
    }

}