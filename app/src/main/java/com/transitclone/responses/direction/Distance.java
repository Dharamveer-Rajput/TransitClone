package com.transitclone.responses.direction;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Surinder Singh Bindra on 02/05/18.
 */

public class Distance {

    @SerializedName("value")
    private int value;

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
