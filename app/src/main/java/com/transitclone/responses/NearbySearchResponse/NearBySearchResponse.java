package com.transitclone.responses.NearbySearchResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Surinder Singh Bindra on 02/16/18.
 */

public class NearBySearchResponse {
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("results")
    private List<Results> results;
    @Expose
    @SerializedName("html_attributions")
    private List<String> html_attributions;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<String> html_attributions) {
        this.html_attributions = html_attributions;
    }
}
