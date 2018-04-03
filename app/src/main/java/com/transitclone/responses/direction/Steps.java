package com.transitclone.responses.direction;

import com.google.gson.annotations.SerializedName;

public class Steps {
    private Location start_location;
    private Location end_location;
    private OverviewPolyLine polyline;


    @SerializedName("duration")
    private Duration duration;

    @SerializedName("distance")
    private Distance distance;


    public String getHtml_instructions() {
        return html_instructions;
    }

    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }

    @SerializedName("html_instructions")
    private String html_instructions;



    public String getTravel_mode() {
        return travel_mode;
    }

    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }

    @SerializedName("travel_mode")
    private String travel_mode;



    public Location getStart_location() {
        return start_location;
    }

    public Location getEnd_location() {
        return end_location;
    }

    public OverviewPolyLine getPolyline() {
        return polyline;
    }

    public Duration getDuration() {
        return duration;
    }

    public Distance getDistance() {
        return distance;
    }






}
