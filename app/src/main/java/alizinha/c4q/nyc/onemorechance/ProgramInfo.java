package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;


public class ProgramInfo extends ActionBarActivity {
    boolean isSpanish = false;

    TextView mDescription;
    TextView mTitle;
    TextView mAddress;
    TextView mPhoneNumber;
    TextView mWebsite;
    String description;
    String title;
    String address;
    String phoneNumber;
    String website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_info);

        description = getIntent().getStringExtra("description");
        title = getIntent().getStringExtra("title");
        address = getIntent().getStringExtra("address");
        phoneNumber = getIntent().getStringExtra("phone_number");
        website = getIntent().getStringExtra("website");

        mWebsite = (TextView) findViewById(R.id.url);
        mWebsite.setText(website);

        mPhoneNumber = (TextView) findViewById(R.id.phone_number);
        mPhoneNumber.setText(phoneNumber);

        mDescription = (TextView) findViewById(R.id.description);
        mDescription.setText(description);

        mTitle = (TextView) findViewById(R.id.titleID);
        mTitle.setText(title);

        mAddress = (TextView) findViewById(R.id.location);
        mAddress.setText(address);
    }
    public void bookAppointment(View v) {
        Intent intent = new Intent(ProgramInfo.this, AddEventToCalendar.class);
        startActivity(intent);
    }



}
