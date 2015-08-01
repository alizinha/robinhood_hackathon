package alizinha.c4q.nyc.onemorechance;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by c4q-anthony-mcbride on 8/1/15.
 */
public interface AuntBerthaService {

    @GET("/zipcodes/{zipcode}/programs")
    public AuntBerthaJSON getInfoByProgramAndZipcode(@Path("zipcode")String zipcode, @Query("api_key") String apiKey);



}
