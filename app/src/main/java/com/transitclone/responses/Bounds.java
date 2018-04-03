package com.transitclone.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bounds {
    @Expose
    @SerializedName("southwest")
    private Southwest southwest;
    @Expose
    @SerializedName("northeast")
    private Northeast northeast;

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }
}
