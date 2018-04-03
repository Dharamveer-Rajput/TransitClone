package com.transitclone.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distance {
    @Expose
    @SerializedName("value")
    private int value;
    @Expose
    @SerializedName("text")
    private String text;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
