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

public class HappeningNow extends Activity {

    private ParseQueryAdapter<ParseObject> mainAdapter;
    private CustomAdapter urgentTodosAdapter;
    private ListView listView;
    
    private static final String TAG = "HappeningNow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.happening_now); 
        ParseQuery<ParseObject> q = ParseQuery.getQuery("FoodEvent");

        // Initialize the subclass of ParseQueryAdapter
        urgentTodosAdapter = new CustomAdapter(this, CustomAdapter.ContextType.HAPPENING_NOW);

        // Initialize ListView and set initial view to mainAdapter
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(urgentTodosAdapter);
    }

}