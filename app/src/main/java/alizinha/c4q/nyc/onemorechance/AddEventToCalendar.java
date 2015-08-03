package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
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
    private EditText mNewTitle;
    private EditText mNewLocation;
    private EditText mNewDescription;
    boolean isSpanish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event_to_calendar);

        isSpanish = getIntent().getFlags() == 1;

        mButtonSubmit = (Button) findViewById(R.id.submit);
        mEditTextNewTitle = (TextView) findViewById(R.id.title);
        mEditTextNewEvent = (TextView) findViewById(R.id.evento);
        mNewTitle = (EditText) findViewById(R.id.newTitle);
        mNewLocation = (EditText) findViewById(R.id.newLocation);
        mNewDescription = (EditText) findViewById(R.id.newDescription);

        if (isSpanish) {
            mNewTitle.setHint("Titulo de Nuevo Evento");
            mNewLocation.setHint("Ubicación");
            mNewDescription.setHint("Descripción");
            mButtonSubmit.setText("Crear Evento");
            mEditTextNewEvent.setText("Añadir Evento");
        }

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mNewTitle.getText() != null) {
                    getTitle = mNewTitle.getText().toString();
                } else {
                    getTitle = "";
                }


                if (mNewLocation.getText() != null) {
                    getLocation = mNewLocation.getText().toString();
                } else {
                    getLocation = "";
                }


                if (mNewDescription.getText() != null) {
                    getDescription = mNewDescription.getText().toString();
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


