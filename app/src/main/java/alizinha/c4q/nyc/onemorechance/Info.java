package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Info extends AppCompatActivity{
    LinearLayout advancedSearchLayout;
    EditText nameEditText, zipcodeEditText, age;
    String nameString, zipcodeString, ageString, immigrationStatus;
    CheckBox undocumented, immigrants, refugee, latinoYes, latinoNo, english, spanish, both;
    boolean isSpanish = false;
    Button search, advancedSearch;
    TextView preferredProgramLanguage, citizenStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        initializeViews();

        isSpanish = getIntent().getFlags() == 1;

        advancedSearchLayout.setVisibility(View.GONE);

        if (isSpanish) {

            nameEditText.setText("Nombre");
            age.setText("Edad");
            zipcodeEditText.setText("Codigo Postal");
            undocumented.setText("Indocumentado");
            immigrants.setText("Inmigrante");
            refugee.setText("Refujiado");
            latinoYes.setText("Si");
            search.setText("Buscar");
            advancedSearch.setText("Busqueda Avanzada");
            preferredProgramLanguage.setText("Preferencia de Lenguaje");
            citizenStatus.setText("Status Migratorio");

        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                    intent.setFlags(1);
                    startActivity(intent);

            }
        });
        
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
        search = (Button) findViewById(R.id.searchButtonID);
        advancedSearch = (Button) findViewById(R.id.advancedSearchButtonID);
        preferredProgramLanguage = (TextView) findViewById(R.id.preferredProgramLanguageID);
        citizenStatus = (TextView) findViewById(R.id.citizenStatusID);

    }

    public void advancedSearch (View view) {
        advancedSearchLayout.setVisibility(View.VISIBLE);
        
    }
}
