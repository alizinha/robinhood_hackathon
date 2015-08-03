package alizinha.c4q.nyc.onemorechance;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Allison Bojarski on 8/1/15.
 */


public class AddEventToCalendar extends ActionBarActivity {
    private Button mButtonSubmit;
    private TextView mEditTextNewTitle;
    private TextView mEditTextNewLocation;
    private TextView mEditTextDescription;
    private TextView mEditTextNewEvent;
    private String getTitle;
    private String getLocation;
    private String getDescription;
    boolean isSpanish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_to_calendar);

        isSpanish = getIntent().getFlags() == 1;

        mButtonSubmit = (Button) findViewById(R.id.submit);
        mEditTextNewTitle = (TextView) findViewById(R.id.title);
        mEditTextNewLocation = (TextView) findViewById(R.id.location_title);
        mEditTextDescription = (TextView) findViewById(R.id.description_spanol);
        mEditTextNewEvent = (TextView)findViewById(R.id.evento);

        if(isSpanish) {
            mEditTextNewTitle.setText("Titulo de Nuevo Evento");
            mEditTextNewLocation.setText("Ubicación");
            mEditTextDescription.setText("Descripción");
            mButtonSubmit.setText("Crear Evento");
            mEditTextNewEvent.setText("Añadir Evento");
        }

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEditTextNewTitle.getText() != null) {
                    getTitle = mEditTextNewTitle.getText().toString();
                } else {
                    getTitle = "";
                }


                if (mEditTextNewLocation.getText() != null) {
                    getLocation = mEditTextNewLocation.getText().toString();
                } else {
                    getLocation = "";
                }


                if (mEditTextDescription.getText() != null) {
                    getDescription = mEditTextDescription.getText().toString();
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


