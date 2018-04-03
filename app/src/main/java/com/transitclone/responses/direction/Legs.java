package com.transitclone.responses.direction;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Legs {

    @SerializedName("steps")
    private List<Steps> steps;

    public List<Steps> getSteps() {
        return steps;
    }
}
