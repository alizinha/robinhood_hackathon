package alizinha.c4q.nyc.onemorechance;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by c4q-anthony-mcbride on 8/2/15.
 */
public class Program {

    public ArrayList<Office> offices;
    public String description;

    @SerializedName("website_url")
    public String website;

}
