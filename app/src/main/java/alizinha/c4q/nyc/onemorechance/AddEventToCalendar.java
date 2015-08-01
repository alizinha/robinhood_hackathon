package alizinha.c4q.nyc.onemorechance;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Allison Bojarski on 8/1/15.
 */


public class AddEventToCalendar extends ActionBarActivity {
    Button submit;
    EditText newTitle;
    EditText newLocation;
    EditText description;
    String getTitle;
    String getLocation;
    String getDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_to_calendar);
        Button submit = (Button) findViewById(R.id.submit);
        newTitle = (EditText) findViewById(R.id.newTitle);
        newLocation = (EditText) findViewById(R.id.newLocation);
        description = (EditText) findViewById(R.id.newDescription);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (newTitle.getText() != null) {
                    getTitle = newTitle.getText().toString();
                } else {
                    getTitle = "";
                }


                if (newLocation.getText() != null) {
                    getLocation = newLocation.getText().toString();
                } else {
                    getLocation = "";
                }


                if (description.getText() != null) {
                    getDescription = description.getText().toString();
                } else {
                    getDescription = "";
                }

                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, getTitle);
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, getLocation);
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, getDescription);
                startActivity(calIntent);
            }
        });

    }
}


