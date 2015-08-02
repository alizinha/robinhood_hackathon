package alizinha.c4q.nyc.onemorechance;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;


public class ProgramInfo extends ActionBarActivity {
    boolean isSpanish = false;
    private TextView mTextViewAppointment;
    private TextView mButtonFeedBack;
    private TextView mButtonDescription;
    private String textToTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_info);

        isSpanish = getIntent().getFlags() == 1;

        mButtonFeedBack = (TextView) findViewById(R.id.button_feed_back);
        mButtonDescription = (TextView) findViewById(R.id.description);

        if (isSpanish) {
         //   mTextViewAppointment.setText("Crear Cita");
            new MyAsyncTask() {
                protected void onPostExecute(Boolean result) {
                    mButtonDescription.setText(textToTranslate);
                }
            }.execute();

           // mButtonDescription.setText(textToTranslate);


        }
        mTextViewAppointment = (TextView) findViewById(R.id.appointment_button);
        mButtonFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

    }


        class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
            @Override
            protected Boolean doInBackground(Void... arg0) {
                Translate.setClientId("onemorechance2015");
                Translate.setClientSecret("VsV3a//xYxb9YAa88czH4+1pfdo5TAWyWQ25jtA6vDA=");
                try {
                    textToTranslate = Translate.execute(mButtonDescription.getText().toString(), Language.ENGLISH, Language.SPANISH);

                } catch(Exception e) {
                    textToTranslate = e.toString();
                }
                return true;
            }
        }

    }


