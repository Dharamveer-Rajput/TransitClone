package com.transitclone.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Geocoded_waypoints {
    @Expose
    @SerializedName("types")
    private List<String> types;
    @Expose
    @SerializedName("place_id")
    private String place_id;
    @Expose
    @SerializedName("geocoder_status")
    private String geocoder_status;

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getGeocoder_status() {
        return geocoder_status;
    }

    public void setGeocoder_status(String geocoder_status) {
        this.geocoder_status = geocoder_status;
    }
}
