package alizinha.c4q.nyc.onemorechance;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView englishTextView, spanishTextView, nameTextView;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings;// = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
        settings = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
        name = settings.getString("name", "");
        Intent intent = new Intent(MainActivity.this, SearchResults.class);

        if (!name.equals("")){
            startActivity(intent);
        }


        englishTextView = (TextView) findViewById(R.id.english_button);
        spanishTextView = (TextView) findViewById(R.id.spanish_button);
        nameTextView = (TextView) findViewById(R.id.setNameID);

        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                startActivity(intent);

            }
        });

        englishTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                startActivity(intent);

            }
        });
        spanishTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                startActivity(intent);

            }
        });

        try {
            //SharedPreferences settings;// = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
            settings = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
            name = settings.getString("name", "");
            //button.setVisibility(View.VISIBLE);
            nameTextView.setText(name);
            if (nameTextView.getText().toString().equals("")) {
                nameTextView.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            nameTextView.setVisibility(View.GONE);
        }
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
