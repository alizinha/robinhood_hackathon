package alizinha.c4q.nyc.onemorechance;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by c4q-anthony-mcbride on 8/1/15.
 */
public interface AuntBerthaService {

    @GET("/zipcodes/{zipcode}/programs")
    void getProgramByZipcode(@Path("zipcode")String zipcode, @Query("api_key") String apiKey, @Query("serviceTag") String tags, @Query("cursor") int cursor, @Query("limit") int limit, Callback<APIData> cb);



}