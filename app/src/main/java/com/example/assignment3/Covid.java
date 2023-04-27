package com.example.assignment3;

import com.google.gson.annotations.SerializedName;

public class Covid {
    @SerializedName("recovered")
    private int recovered;

    @SerializedName("cases")
    private int cases;

    @SerializedName("deaths")
    private int deaths;


    public int getRecovered(){
        return recovered;
    }

    public int getCases(){
        return cases;
    }

    public int getDeaths(){
        return deaths;
    }
}
