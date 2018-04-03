package com.transitclone.responses;

/**
 * Created by Surinder Singh Bindra on 02/15/18.
 */

public class TransitResponse {
    @com.google.gson.annotations.Expose
    @com.google.gson.annotations.SerializedName("geocoded_waypoints")
    private java.util.List<Geocoded_waypoints> geocoded_waypoints;
}
