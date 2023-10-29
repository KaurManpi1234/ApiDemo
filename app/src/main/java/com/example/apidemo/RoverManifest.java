package com.example.apidemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoverManifest {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("landing_date")
    @Expose
    private String landingDate;

    @SerializedName("launch_date")
    @Expose
    private String launchDate;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("max_date")
    @Expose
    private String maxDate;

    @SerializedName("total_photos")
    @Expose
    private int totalPhotos;

    public RoverManifest(String name, String landingDate, String launchDate, String status, String maxDate, int totalPhotos) {
        this.name = name;
        this.landingDate = landingDate;
        this.launchDate = launchDate;
        this.status = status;
        this.maxDate = maxDate;
        this.totalPhotos = totalPhotos;
    }

    public RoverManifest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(String landingDate) {
        this.landingDate = landingDate;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public int getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(int totalPhotos) {
        this.totalPhotos = totalPhotos;
    }
}
