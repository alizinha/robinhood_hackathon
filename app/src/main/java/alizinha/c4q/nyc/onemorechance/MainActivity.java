package alizinha.c4q.nyc.onemorechance;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import retrofit.RestAdapter;


public class MainActivity extends ActionBarActivity {

    Button test;
    AuntBerthaJSON json = new AuntBerthaJSON();
    private static final String API_KEY= "75ea03a922dc66db2560a23cc4eed49e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        json.setName("Hello, World!");

        test = (Button) findViewById(R.id.spanish_button);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("https://searchbertha-hrd.appspot.com/_ah/api/search/v1")
                        .build();

                AuntBerthaService service = restAdapter.create(AuntBerthaService.class);

                service.getInfoByProgramAndZipcode("11101", "ahJzfnNlYXJjaGJlcnRoYS1ocmRyFAsSB1Byb2dyYW0YgICAiNboxgoM", API_KEY);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
