package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SearchResults extends ActionBarActivity {
    ListView lv;
    private TextView mTextViewSearchResults;
    private Button mButtonNewSearch;
    boolean isSpanish = false;
    Button app;

    public static final String BASE_URL = "https://searchbertha-hrd.appspot.com/_ah/api/search/v1/";
    private static final String API_KEY = "75ea03a922dc66db2560a23cc4eed49e";

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    AuntBerthaService service = restAdapter.create(AuntBerthaService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        isSpanish = getIntent().getFlags() == 1;

        mTextViewSearchResults = (TextView) findViewById(R.id.text_view_search_result);
        mButtonNewSearch = (Button) findViewById(R.id.button_new_search);

        if (isSpanish) {
            mTextViewSearchResults.setText("Resultados");
            mButtonNewSearch.setText("Nueva Busqueda");
        }

        mButtonNewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResults.this, Info.class);
                if (isSpanish) {
                    intent.setFlags(1);
                    startActivity(intent);
                } else {
                    startActivity(intent);
                }
            }
        });

        lv = (ListView) findViewById(R.id.list_item);

        loadSearchResults();

    }

    public void loadSearchResults() {
        String zipCode = getIntent().getStringExtra("zipcode");
        Log.v("Tags", zipCode);

        service.getProgramByZipcode(zipCode, API_KEY, "mentoring,ged/high-school%20equivalency", 0, 10, new Callback<APIData>() {

            @Override
            public void success(final APIData apiData, Response response) {
                //Toast.makeText(getApplicationContext(), apiData.programs.get(0).offices.get(0).name, Toast.LENGTH_LONG).show();
                lv = (ListView) findViewById(R.id.list_item);

                ArrayList<String> your_array_list = new ArrayList<String>();

                final Office[] offices = new Office[10];
                for (int i = 0; i < 10; i++) {
                    Office o = new Office();

                    o.name = apiData.programs.get(i).offices.get(0).name;
                    o.address1 = apiData.programs.get(i).offices.get(0).address1 + "\n"
                            + apiData.programs.get(i).offices.get(0).city + ", "
                            + apiData.programs.get(i).offices.get(0).state;

                    offices[i] = o;
                }

                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), R.layout.row, offices);

                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String description = apiData.programs.get(i).description;
                        String website = apiData.programs.get(i).website;
                        String title = apiData.programs.get(i).offices.get(0).name;
                        String phoneNumber = apiData.programs.get(i).offices.get(0).phoneNumber;
                        String address = apiData.programs.get(i).offices.get(0).address1 + "\n"
                                + apiData.programs.get(i).offices.get(0).city + ", "
                                + apiData.programs.get(i).offices.get(0).state;

                        Intent intent = new Intent(SearchResults.this, ProgramInfo.class);

                        if (isSpanish) {
                            intent.putExtra("description", description);
                            intent.putExtra("title", title);
                            intent.putExtra("address", address);
                            intent.putExtra("phone_number", phoneNumber);
                            intent.putExtra("website", website);
                            intent.setFlags(1);
                            startActivity(intent);

                        } else {
                            intent.putExtra("description", description);
                            intent.putExtra("title", title);
                            intent.putExtra("address", address);
                            intent.putExtra("phone_number", phoneNumber);
                            intent.putExtra("website", website);
                            startActivity(intent);
                        }
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
                Log.w("TAG", error.toString());

            }
        });
    }
}
