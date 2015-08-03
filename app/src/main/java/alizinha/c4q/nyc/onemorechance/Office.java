package alizinha.c4q.nyc.onemorechance;

import com.google.gson.annotations.SerializedName;

/**
 * Created by c4q-anthony-mcbride on 8/2/15.
 */
public class Office {

    public String name;
    public String city;
    public String address1;
    public String state;

    @SerializedName("phone_number")
    public String phoneNumber;
}
