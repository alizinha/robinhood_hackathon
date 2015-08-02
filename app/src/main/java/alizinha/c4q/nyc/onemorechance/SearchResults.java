package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;


public class SearchResults extends ActionBarActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        lv = (ListView) findViewById(R.id.list_item);

        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("foo");
        your_array_list.add("bar");
        your_array_list.add("bar");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

        lv.setAdapter(arrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void bookAppointment(View v) {
        Intent intent = new Intent(SearchResults.this, AddEventToCalendar.class);
        startActivity(intent);
    }

    public void giveFeedbackOnAppointment(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"support@auntbertha.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Change Request C4Q");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "If you'd like to give feedback on a particular service, please write \n" +
                "their name and give a rating between one (worst) and five (best) stars. If you found any wrong information \n" +
                "in our app as to when a location is open, etc., please provide that feedback here. Thank you.");
        startActivity(emailIntent);
    }


    public void newSearch (View view){
        Intent intent = new Intent (SearchResults.this, Info.class);
        startActivity(intent);
    }
}
