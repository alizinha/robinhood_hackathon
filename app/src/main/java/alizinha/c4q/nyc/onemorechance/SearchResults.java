package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;


public class SearchResults extends ActionBarActivity {
    ListView lv;
    private TextView mTextViewSearchResults;
    private Button mButtonNewSearch;
    boolean isSpanish = false;
    Button app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        isSpanish = getIntent().getFlags() == 1;

        mTextViewSearchResults = (TextView) findViewById(R.id.text_view_search_result);
        mButtonNewSearch = (Button) findViewById(R.id.button_new_search);

        if (isSpanish) {
            mTextViewSearchResults.setText("Resultados Encontrados");
            mButtonNewSearch.setText("Nueva Busqueda");
        }

        mButtonNewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResults.this, Info.class);
                startActivity(intent);
            }
        });

        lv = (ListView) findViewById(R.id.list_item);

        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("foo");
        your_array_list.add("bar");
        your_array_list.add("bar");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list);

        lv.setAdapter(arrayAdapter);
    }


    public void bookAppointment(View v) {
        Intent intent = new Intent(SearchResults.this, AddEventToCalendar.class);
        startActivity(intent);
    }

    public void giveFeedbackOnAppointment(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@auntbertha.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Change Request C4Q");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "If you'd like to give feedback on a particular service, please write \n" +
                "their name and give a rating between one (worst) and five (best) stars. If you found any wrong information \n" +
                "in our app as to when a location is open, etc., please provide that feedback here. Thank you.");
        startActivity(emailIntent);
    }
}
