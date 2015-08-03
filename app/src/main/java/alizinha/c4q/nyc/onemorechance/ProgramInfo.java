package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import org.apache.http.protocol.HTTP;


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


    }

    public void giveFeedbackOnAppointment(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@auntbertha.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Change Request C4Q");
        if (isSpanish) {
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Me gustaría dar le una clasificación de (por favor entre un numero entre medio 1 pero y 5 mejor) ");
            startActivity(emailIntent);
        } else {
            emailIntent.putExtra(Intent.EXTRA_TEXT, "If you'd like to give feedback on a particular service, please write \n" +
                    "their name and give a rating between one (worst) and five (best) stars. If you found any wrong information \n" +
                    "in our app as to when a location is open, etc., please provide that feedback here. Thank you.");

            //I would like to give "(    )" a rating of "(please enter a number in-bewteen 1 (worst) and 5 (best))" If you found any wrong information
            //in our app as to when a location is open, etc., please provide that feedback here. Thank you.");

            startActivity(emailIntent);
        }

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

            } catch (Exception e) {
                textSpanish = e.toString();
            }
            return true;
        }
    }
}
