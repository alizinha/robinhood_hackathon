package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Info extends AppCompatActivity {
    LinearLayout advancedSearchLayout;
    EditText nameEditText, zipcodeEditText, age;
    String nameString, zipcodeString, ageString, immigrationStatus;
    CheckBox undocumented, immigrants, refugee, latinoYes, latinoNo, english, spanish, both;
    boolean isSpanish = false;
    //Button search, advancedSearch;
    TextView preferredProgramLanguage, citizenStatus;
    FloatingActionButton sb, asb, sb2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        initializeViews();

        isSpanish = getIntent().getFlags() == 1;

        advancedSearchLayout.setVisibility(View.GONE);

        if (isSpanish) {

            age.setText("Edad");
            zipcodeEditText.setHint("Codigo Postal");
            undocumented.setText("Indocumentado");
            immigrants.setText("Inmigrante");
            refugee.setText("Refujiado");
            latinoYes.setText("Si");
            //search.setText("Buscar");
            //advancedSearch.setText("Busqueda Avanzada");
            preferredProgramLanguage.setText("Preferencia de Lenguaje");
            citizenStatus.setText("Status Migratorio");

        }

    }


    public void initializeViews() {
        advancedSearchLayout = (LinearLayout) findViewById(R.id.advancedSearchLayoutID);
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
        preferredProgramLanguage = (TextView) findViewById(R.id.preferredProgramLanguageID);
        citizenStatus = (TextView) findViewById(R.id.citizenStatusID);
        sb = (FloatingActionButton) findViewById(R.id.searchButtonID);
        asb = (FloatingActionButton) findViewById(R.id.advancedSearchButtonID);
        sb2 = (FloatingActionButton) findViewById(R.id.searchButtonID2);

    }

    public void advancedSearch(View view) {
        advancedSearchLayout.setVisibility(View.VISIBLE);
        sb.setVisibility(View.GONE);
        asb.setVisibility(View.GONE);
        sb2.setVisibility(View.VISIBLE);

    }

    public void search(View view) {



        zipcodeString = zipcodeEditText.getText().toString();
        ageString = age.getText().toString();

        SharedPreferences info;
        info = Info.this.getSharedPreferences("PREFS_NAME", 0);
        SharedPreferences.Editor editor = info.edit();

        editor.putString("name", nameString);
        editor.putString("zipcode", zipcodeString);
        editor.putString("age", ageString);


        if (undocumented.isChecked() == true) {
            immigrationStatus = "undocumented";
        } else if (immigrants.isChecked() == true) {
            immigrationStatus = "immigrant";
        } else {
            immigrationStatus = "refugee";
        }

        editor.putString("immigrationStatus", immigrationStatus);
        editor.commit();


        Intent intent = new Intent(Info.this, SearchResults.class);
        if (isSpanish) {
            intent.setFlags(1);
            intent.putExtra("zipcode", zipcodeString);
            startActivity(intent);
        } else {
            intent.putExtra("zipcode", zipcodeString);
            startActivity(intent);
        }

    }

}
