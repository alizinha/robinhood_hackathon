package alizinha.c4q.nyc.onemorechance;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.squareup.okhttp.Response;

//import javax.security.auth.callback.Callback;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    Button test;

    public static final String BASE_URL = "https://searchbertha-hrd.appspot.com/_ah/api/search/v1/";
    private static final String API_KEY = "75ea03a922dc66db2560a23cc4eed49e";

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(BASE_URL)
            .build();

    AuntBerthaService service = restAdapter.create(AuntBerthaService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = (Button) findViewById(R.id.spanish_button);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zipCode = "11101";

                service.getProgramByZipcode(zipCode, API_KEY, 0, 10, new Callback<APIData>() {


                    @Override
                    public void success(APIData apiData, Response response) {
                        Toast.makeText(getApplicationContext(), apiData.programs.get(0).offices.get(0).name, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), "Failire", Toast.LENGTH_LONG).show();

                    }
                });


            }
        });

    }




}