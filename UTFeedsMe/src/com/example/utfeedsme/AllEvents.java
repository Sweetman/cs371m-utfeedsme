package com.example.utfeedsme;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class AllEvents extends ListActivity {
	
	private final static String TAG = "AllEvents";
	
	//private static final int DIALOG_SORT = 0;
	protected RecordsDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_events);
		
		 ParseQueryAdapter.QueryFactory<ParseObject> factory =
			     new ParseQueryAdapter.QueryFactory<ParseObject>() {
			       public ParseQuery<ParseObject> create() {
			         ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("FoodEvent");
			         query.orderByAscending("event");
			         return query;
			       }
			     };

		ParseQueryAdapter<ParseObject> mainAdapter = new ParseQueryAdapter<ParseObject>(this, factory);
		mainAdapter.setTextKey("event");
		setListAdapter(mainAdapter);
		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Log.d(TAG, "selected settings");
            return true;
        }
        if (id == R.id.sort_pref) {
        	Log.d(TAG, "selected to sort");
        	//showDialog(DIALOG_SORT);
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
/*	
	protected Dialog onCreateDialog(int id) {
		
		Log.d(TAG, "In onCreateDialog");
		
		Dialog dialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		switch(id) {
		case DIALOG_SORT:

			builder.setTitle(R.string.sort_string);

			final CharSequence[] sortPrefs = {
					getResources().getString(R.string.sort_start_time),
					getResources().getString(R.string.sort_end_time), 
					getResources().getString(R.string.sort_distance),
					getResources().getString(R.string.sort_location),
					getResources().getString(R.string.sort_type)};

			builder.setSingleChoiceItems(sortPrefs, selected, 
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					dialog.dismiss();   // Close dialog

					mGame.setDifficultyLevel(TicTacToeGame.DifficultyLevel.values()[item]);
					Log.d(TAG, "Difficulty level: " + mGame.getDifficultyLevel());

					// Display the selected difficulty level
					Toast.makeText(getApplicationContext(), levels[item], 
							Toast.LENGTH_SHORT).show();        	    
				}
			});
			
			dialog = builder.create();
			break;    // this case

		}
	}
*/	
}
