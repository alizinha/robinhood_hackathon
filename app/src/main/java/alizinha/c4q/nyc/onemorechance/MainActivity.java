package alizinha.c4q.nyc.onemorechance;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.squareup.okhttp.Response;
//import javax.security.auth.callback.Callback;


public class MainActivity extends Activity {

    Button test;
    TextView englishTextView, spanishTextView;
    String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences settings;// = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
        settings = MainActivity.this.getSharedPreferences("PREFS_NAME", 0);
        name = settings.getString("name", "");

        englishTextView = (TextView) findViewById(R.id.english_button);
        spanishTextView = (TextView) findViewById(R.id.spanish_button);


        englishTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                intent.setFlags(0);
                startActivity(intent);

            }
        });
        spanishTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Info.class);
                intent.setFlags(1);
                startActivity(intent);

            }
        });


    }
}
