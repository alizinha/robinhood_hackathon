package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;


public class ProgramInfo extends ActionBarActivity {
    boolean isSpanish = false;

    private TextView mDescription;
    private TextView mTitle;
    private TextView mAddress;
    private TextView mPhoneNumber;
    private TextView mWebsite;

    private Button mButtonBio;

    private String description;
    private String title;
    private String address;
    private String phoneNumber;
    private String website;
    private String textSpanish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_info);

        isSpanish = getIntent().getFlags() == 1;

        description = getIntent().getStringExtra("description");
        title = getIntent().getStringExtra("title");
        address = getIntent().getStringExtra("address");
        phoneNumber = getIntent().getStringExtra("phone_number");
        website = getIntent().getStringExtra("website");


        mButtonBio = (Button) findViewById(R.id.button_bio);
        mWebsite = (TextView) findViewById(R.id.url);
        mPhoneNumber = (TextView) findViewById(R.id.phone_number);
        mDescription = (TextView) findViewById(R.id.description);
        mTitle = (TextView) findViewById(R.id.titleID);
        mAddress = (TextView) findViewById(R.id.location);
        mWebsite.setText(website);
        mPhoneNumber.setText(phoneNumber);
        mTitle.setText(title);
        mAddress.setText(address);


        mDescription.setText(description);

        if (isSpanish) {

            mButtonBio.setText("Biografia");

            new MyAsyncTask() {
                protected void onPostExecute(Boolean result) {

                    mDescription.setText(textSpanish);
                }
            }.execute();
        }

        mButtonBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProgramInfo.this, BiographyClass.class);
                if (isSpanish) {
                    intent.setFlags(1);
                    startActivity(intent);
                }else {
                    startActivity(intent);
                }
            }
        });
    }

    public void bookAppointment(View v) {
        Intent intent = new Intent(ProgramInfo.this, AddEventToCalendar.class);
        startActivity(intent);

    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... arg0) {
            Translate.setClientId("onemorechance2015");
            Translate.setClientSecret("VsV3a//xYxb9YAa88czH4+1pfdo5TAWyWQ25jtA6vDA=");
            try {
                textSpanish = Translate.execute(mDescription.getText().toString(), Language.ENGLISH, Language.SPANISH);

            } catch(Exception e) {
                textSpanish = e.toString();
            }
            return true;
        }
    }
}
