package com.transitclone.responses.direction;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rajsm on 01/11/2018.
 */

public class GoogleDirectionResponse {
    @SerializedName("routes")
    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }}

