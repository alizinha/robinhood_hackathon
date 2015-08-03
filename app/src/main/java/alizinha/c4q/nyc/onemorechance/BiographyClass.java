package alizinha.c4q.nyc.onemorechance;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class BiographyClass extends ActionBarActivity {

    boolean isSpanish = false;
    private TextView mTextViewBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biography_class);

        isSpanish = getIntent().getFlags() == 1;

        mTextViewBio = (TextView) findViewById(R.id.bio_elvis);

        if (isSpanish) {
            mTextViewBio.setText(R.string.spanol_bio_elvis);
        }else {
            mTextViewBio.setText(R.string.bio_elvis);
        }
    }
}
