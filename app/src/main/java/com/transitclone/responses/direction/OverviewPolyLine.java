package com.transitclone.responses.direction;

import com.google.gson.annotations.SerializedName;

public class OverviewPolyLine {

    @SerializedName("points")
    public String points;

    public String getPoints() {
        return points;
    }
}
