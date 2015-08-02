package alizinha.c4q.nyc.onemorechance;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ProgramInfo extends ActionBarActivity {
    boolean isSpanish = false;
    private TextView mTextViewAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_info);

        isSpanish = getIntent().getFlags() == 1;

        if(isSpanish) {
            mTextViewAppointment.setText("Crear Cita");
        }
        mTextViewAppointment = (TextView)findViewById(R.id.appointment_button);

    }

}
