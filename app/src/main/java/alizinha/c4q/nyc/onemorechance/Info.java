package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;


public class Info extends ActionBarActivity {
    LinearLayout advancedSearchLayout;
    EditText nameEditText, zipcodeEditText, age;
    String nameString, zipcodeString, ageString, immigrationStatus;
    CheckBox undocumented, immigrants, refugee, latinoYes, latinoNo, english, spanish, both;


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
        age = (EditText) findViewById(R.id.ageID);
        undocumented = (CheckBox) findViewById(R.id.undocumentedID);
        immigrants = (CheckBox) findViewById(R.id.immigrantsID);
        refugee = (CheckBox) findViewById(R.id.refugeeID);
        latinoYes = (CheckBox) findViewById(R.id.latinoYesID);
        latinoNo = (CheckBox) findViewById(R.id.latinoNoID);
        english = (CheckBox) findViewById(R.id.englishID);
        spanish = (CheckBox) findViewById(R.id.spanishID);
        both = (CheckBox) findViewById(R.id.bothID);
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
        ageString = age.getText().toString();

        SharedPreferences info;
        info = Info.this.getSharedPreferences("PREFS_NAME", 0);
        SharedPreferences.Editor editor = info.edit();

        editor.putString("name", nameString);
        editor.putString("zipcode", zipcodeString);
        editor.putString("age", ageString);


        if(undocumented.isChecked() == true){
            immigrationStatus = "undocumented";
        }
        else if (immigrants.isChecked() == true){
            immigrationStatus = "immigrant";
        }
        else {
            immigrationStatus = "refugee";
        }

        editor.putString("immigrationStatus", immigrationStatus);
        editor.commit();

        Intent intent = new Intent(Info.this, SearchResults.class);
        startActivity(intent);
    }
    public void advancedSearch (View view) {
        advancedSearchLayout.setVisibility(View.VISIBLE);
    }
}
