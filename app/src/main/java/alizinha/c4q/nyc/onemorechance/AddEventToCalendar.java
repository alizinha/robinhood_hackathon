package alizinha.c4q.nyc.onemorechance;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Allison Bojarski on 8/1/15.
 */


public class AddEventToCalendar extends ActionBarActivity {
    private Button mButtonSubmit;
    private EditText mEditTextNewTitle;
    private EditText mEditTextNewLocation;
    private EditText mEditTextDescription;
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
        mEditTextNewTitle = (EditText) findViewById(R.id.newTitle);
        mEditTextNewLocation = (EditText) findViewById(R.id.newLocation);
        mEditTextDescription = (EditText) findViewById(R.id.newDescription);

        if(isSpanish) {
            mEditTextNewTitle.setHint("Titulo de Nuevo Evento");
            mEditTextNewLocation.setHint("Ubicación");
            mEditTextDescription.setHint("Descripción");
            mButtonSubmit.setText("Crear Evento");
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


