package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;


public class Info extends ActionBarActivity {
    LinearLayout advancedSearchLayout;
    EditText nameEditText, zipcodeEditText;
    String nameString, zipcodeString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        initializeViews();

        advancedSearchLayout.setVisibility(View.GONE);
        
    }

    public void initializeViews() {
        advancedSearchLayout = (LinearLayout) findViewById(R.id.advancedSearchLayoutID);
        nameEditText = (EditText) findViewById(R.id.nameID);
        zipcodeEditText = (EditText) findViewById(R.id.zipcodeID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
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

    public void toResults(View view) {
        nameString = nameEditText.getText().toString();
        zipcodeString = zipcodeEditText.getText().toString();

        SharedPreferences info;
        info = Info.this.getSharedPreferences("PREFS_NAME", 0);
        SharedPreferences.Editor editor = info.edit();

        editor.putString("name", nameString);
        editor.putString("zipcode", zipcodeString);
        editor.commit();

        Intent intent = new Intent(Info.this, SearchResults.class);
        startActivity(intent);
    }
    public void advancedSearch (View view) {
        advancedSearchLayout.setVisibility(View.VISIBLE);
    }
}
