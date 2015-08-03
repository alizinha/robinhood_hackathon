package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
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
      private String description;
      private String title;
      private String address;
      private String phoneNumber;
      private String website;
    String textSpanish;


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
            new MyAsyncTask() {
                protected void onPostExecute(Boolean result) {

                    mDescription.setText(textSpanish);
                }
            }.execute();
        }
    }

    public void bookAppointment(View v) {
        Intent intent = new Intent(ProgramInfo.this, AddEventToCalendar.class);
        intent.setFlags(1);
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
