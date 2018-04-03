package com.transitclone.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Arrival_time {
    @Expose
    @SerializedName("value")
    private int value;
    @Expose
    @SerializedName("time_zone")
    private String time_zone;
    @Expose
    @SerializedName("text")
    private String text;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
