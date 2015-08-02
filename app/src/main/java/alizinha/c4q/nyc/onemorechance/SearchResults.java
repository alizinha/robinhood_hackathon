package alizinha.c4q.nyc.onemorechance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.protocol.HTTP;

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
        app = (Button) findViewById(R.id.button_appt_search);

        if (isSpanish) {
            mTextViewSearchResults.setText("Resultados Encontrados");
            mButtonNewSearch.setText("Nueva Busqueda");
        }

        mButtonNewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResults.this, Info.class);
                if (isSpanish){
                    intent.setFlags(1);
                    startActivity(intent);
                }else {
                    startActivity(intent);
                }
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


        loadSearchResults();



    }

    public void loadSearchResults (){
        String zipCode = getIntent().getStringExtra("zipcode");
        Log.v("Tags", zipCode);

        service.getProgramByZipcode(zipCode, API_KEY, "mentoring,ged/high-school%20equivalency", 0, 10, new Callback<APIData>() {


            @Override
            public void success(APIData apiData, Response response) {
                //Toast.makeText(getApplicationContext(), apiData.programs.get(0).offices.get(0).name, Toast.LENGTH_LONG).show();
                lv = (ListView) findViewById(R.id.list_item);

                ArrayList<String> your_array_list = new ArrayList<String>();

                Office[] offices = new Office[10];
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

            }
            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
                Log.w("TAG", error.toString());

            }
        });
    }


    public void bookAppointment(View v) {
        Intent intent = new Intent(SearchResults.this, AddEventToCalendar.class);
        startActivity(intent);
    }

    public void giveFeedbackOnAppointment(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
        emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@auntbertha.com"}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Change Request C4Q");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "If you'd like to give feedback on a particular service, please write \n" +
                "their name and give a rating between one (worst) and five (best) stars. If you found any wrong information \n" +
                "in our app as to when a location is open, etc., please provide that feedback here. Thank you.");
        startActivity(emailIntent);
    }

}
