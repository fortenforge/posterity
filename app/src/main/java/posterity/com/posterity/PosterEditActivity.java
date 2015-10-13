package posterity.com.posterity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.List;

public class PosterEditActivity extends AppCompatActivity {

    private static PosterEvent posterEvent;

    private static int eventNum;

    private static TextView editStartTime;
    private static TextView editEndTime;
    private static TextView editDate;

    private static int tempEventTimeStartHour;
    private static int tempEventTimeStartMinute;
    private static int tempEventTimeEndHour;
    private static int tempEventTimeEndMinute;
    private static int tempEventDateYear;
    private static int tempEventDateMonth;
    private static int tempEventDateDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Edit Event");
        }

        final PosterHelper posterHelper = new PosterHelper(getApplicationContext());
        List<PosterEvent> posterData = posterHelper.queryAll();
        eventNum = getIntent().getIntExtra("eventnum", 0);
        posterEvent = posterData.get(eventNum);

        final String imageName = posterEvent.getImageName();
        List<Integer> eventTimes = posterEvent.getTimeRange();
        tempEventTimeStartHour = eventTimes.get(0);
        tempEventTimeStartMinute = eventTimes.get(1);
        tempEventTimeEndHour = eventTimes.get(2);
        tempEventTimeEndMinute = eventTimes.get(3);
        List<Integer> eventDate = posterEvent.getDate();
        tempEventDateYear = eventDate.get(0);
        tempEventDateMonth = eventDate.get(1);
        tempEventDateDay = eventDate.get(2);

        final EditText editTitle = (EditText) findViewById(R.id.edit_title);
        editStartTime = (TextView) findViewById(R.id.edit_start_time);
        editEndTime = (TextView) findViewById(R.id.edit_end_time);
        editDate = (TextView) findViewById(R.id.edit_date);
        final EditText editLocation = (EditText) findViewById(R.id.edit_location);

        editTitle.setText(posterEvent.getEventTitle(), TextView.BufferType.EDITABLE);
        editStartTime.setText(posterEvent.getStartTimeRangeString());
        editEndTime.setText(posterEvent.getEndTimeRangeString());
        editDate.setText(posterEvent.getDateString());
        editLocation.setText(posterEvent.getLocation(), TextView.BufferType.EDITABLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempEventTimeStartHour > tempEventTimeEndHour || (tempEventTimeStartHour == tempEventTimeEndHour && tempEventTimeStartMinute > tempEventTimeEndMinute)) {
                    Snackbar.make(v, "Invalid time range", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    posterHelper.updateRow(imageName, editTitle.getText().toString(), tempEventTimeStartHour, tempEventTimeStartMinute, tempEventTimeEndHour, tempEventTimeEndMinute, tempEventDateYear, tempEventDateMonth, tempEventDateDay, editLocation.getText().toString());
                    Intent intent = NavUtils.getParentActivityIntent(PosterEditActivity.this);
                    intent.putExtra("eventnum", eventNum);
                    NavUtils.navigateUpTo(PosterEditActivity.this, intent);
                }
            }
        });
    }

    public void showStartTimePickerDialog(View v) {
        DialogFragment dialogFragment = new TimePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "startTimePicker");
    }

    public void showEndTimePickerDialog(View v) {
        DialogFragment dialogFragment = new TimePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "endTimePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int hour;
            int minute;
            if (getTag().equals("startTimePicker")) {
                hour = tempEventTimeStartHour;
                minute = tempEventTimeStartMinute;
            }
            else {
                hour = tempEventTimeEndHour;
                minute = tempEventTimeEndMinute;
            }
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            if (getTag().equals("startTimePicker")) {
                posterEvent.setStartTimes(hourOfDay, minute);
                editStartTime.setText(posterEvent.getStartTimeRangeString());
                tempEventTimeStartHour = hourOfDay;
                tempEventTimeStartMinute = minute;
            }
            else {
                posterEvent.setEndTimes(hourOfDay, minute);
                editEndTime.setText(posterEvent.getEndTimeRangeString());
                tempEventTimeEndHour = hourOfDay;
                tempEventTimeEndMinute = minute;
            }
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int year = tempEventDateYear;
            int month = tempEventDateMonth;
            int day = tempEventDateDay;
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            posterEvent.setDate(year, month, day);
            editDate.setText(posterEvent.getDateString());
            tempEventDateYear = year;
            tempEventDateMonth = month;
            tempEventDateDay = day;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(PosterEditActivity.this);
                intent.putExtra("eventnum", eventNum);
                NavUtils.navigateUpTo(PosterEditActivity.this, intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
