package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    TextView preferredProgramLanguage, citizenStatus;
    FloatingActionButton sb, asb, sb2;
    private Button mButtonBio, locations;
    private String textSpanish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        mButtonBio = (Button) findViewById(R.id.button_bio);
        locations = (Button) findViewById(R.id.button_places);

        initializeViews();

        isSpanish = getIntent().getFlags() == 1;

        advancedSearchLayout.setVisibility(View.GONE);

        if (isSpanish) {

            age.setHint("Edad");
            zipcodeEditText.setHint("Codigo Postal");
            undocumented.setText("Indocumentado");
            immigrants.setText("Inmigrante");
            refugee.setText("Refujiado");
            latinoYes.setText("Si");
            preferredProgramLanguage.setText("Preferencia de Lenguaje");
            citizenStatus.setText("Status Migratorio");
            english.setText("Ingles");
            spanish.setText("Español");
            both.setText("Ambos");
            mButtonBio.setText("Historias de Exito");
            locations.setText("lugares Visitados");



        }


            mButtonBio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Info.this, BiographyClass.class);
                    if (isSpanish) {
                        intent.setFlags(1);
                        startActivity(intent);
                    } else {
                        startActivity(intent);
                    }
                }
            });
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

    public void toMyPlaces (View view) {
        Intent intent = new Intent(Info.this, MyPlaces.class);
        startActivity(intent);
    }

    public void search(View view) {

        zipcodeString = zipcodeEditText.getText().toString();
//        ageString = age.getText().toString();
//
//        SharedPreferences info;
//        info = Info.this.getSharedPreferences("PREFS_NAME", 0);
//        SharedPreferences.Editor editor = info.edit();
//
//        editor.putString("name", nameString);
//        editor.putString("zipcode", zipcodeString);
//        editor.putString("age", ageString);
//
//
//        if (undocumented.isChecked() == true) {
//            immigrationStatus = "undocumented";
//        } else if (immigrants.isChecked() == true) {
//            immigrationStatus = "immigrant";
//        } else {
//            immigrationStatus = "refugee";
//        }
//
//        editor.putString("immigrationStatus", immigrationStatus);
//        editor.commit();

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
