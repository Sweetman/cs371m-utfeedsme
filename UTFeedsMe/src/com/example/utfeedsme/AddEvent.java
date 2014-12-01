package com.example.utfeedsme;
import java.util.Calendar;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import com.parse.ParseObject;
public class AddEvent extends FragmentActivity {
	// private final int MINS_PER_DAY = 1440;
	// protected RecordsDataSource dataSource;
	Button add_event;
	EditText event_name, event_where, event_food;
	Button event_time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("addevent", "entered oncreate for addevent");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_event);
		event_name = (EditText) findViewById(R.id.add_event_name);
		event_where = (EditText) findViewById(R.id.add_event_where);
		event_time = (Button) findViewById(R.id.add_event_time);
		event_food = (EditText) findViewById(R.id.add_event_food);
		Button add_event = (Button) findViewById(R.id.add_event_btn);
		event_time.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new TimePickerFragment();
				newFragment.show(getSupportFragmentManager(), "timePicker");
			}
		});
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
	}
	public class TimePickerFragment extends DialogFragment
	implements TimePickerDialog.OnTimeSetListener {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			//Code based on Android documentation example
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
		}
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			//change text to hour..
			//see what calls this
			// Do something with the time chosen by the user
			Button button = (Button) findViewById(R.id.add_event_time);
			button.setText(new StringBuilder().append(String.valueOf(hourOfDay))
					.append(":").append(String.valueOf(minute)));
		}
	}
}