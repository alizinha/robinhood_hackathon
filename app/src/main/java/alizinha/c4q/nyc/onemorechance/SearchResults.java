package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SearchResults extends ActionBarActivity {
    ListView lv;
    private TextView mTextViewSearchResults;
    private Button mButtonNewSearch;
    boolean isSpanish = false;
    Button app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        isSpanish = getIntent().getFlags() == 1;

        mTextViewSearchResults = (TextView) findViewById(R.id.text_view_search_result);
        mButtonNewSearch = (Button) findViewById(R.id.button_new_search);
        app = (Button)findViewById(R.id.button_appt_search);

        if (isSpanish) {
            mTextViewSearchResults.setText("Resultados Encontrados");
            mButtonNewSearch.setText("Nueva Busqueda");
        }

        mButtonNewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SearchResults.this, Info.class);
                startActivity(intent);
            }
        });

        app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResults.this, AddEventToCalendar.class);
                intent.setFlags(1);
                startActivity(intent);
            }
        });


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

}
