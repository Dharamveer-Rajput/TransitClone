package com.transitclone.responses.NearbySearchResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Opening_hours {
    @Expose
    @SerializedName("weekday_text")
    private List<String> weekday_text;
    @Expose
    @SerializedName("open_now")
    private boolean open_now;

    public List<String> getWeekday_text() {
        return weekday_text;
    }

    public void setWeekday_text(List<String> weekday_text) {
        this.weekday_text = weekday_text;
    }

    public boolean getOpen_now() {
        return open_now;
    }

    public void setOpen_now(boolean open_now) {
        this.open_now = open_now;
    }
}
