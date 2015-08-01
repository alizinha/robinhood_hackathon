package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    Button nameButton;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameButton = (Button) findViewById(R.id.setNameID);

        try {
            SharedPreferences settings;// = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
            settings = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
            name = settings.getString("name", "");
            //button.setVisibility(View.VISIBLE);
            nameButton.setText(name);
            if (nameButton.getText().toString().equals("")){
                nameButton.setVisibility(View.GONE);
            }

        }
        catch (Exception e){
            nameButton.setVisibility(View.GONE);
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
    public void toInfo (View view){
        Intent intent = new Intent(MainActivity.this, Info.class);
        startActivity(intent);
    }
    public void StraightToResults (View view) {
        Intent intent = new Intent(MainActivity.this, SearchResults.class);
        startActivity(intent);
    }
}
