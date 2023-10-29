package com.example.apidemo;



import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class RoverPicture {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("rover")
    @Expose
    private RoverManifest rover;

    @SerializedName("camera")
    @Expose
    private Camera camera;

    @SerializedName("img_src")
    @Expose
    private String image;

    private String date;

    public RoverPicture(int id, RoverManifest rover, Camera camera, String image, String date) {
        this.id = id;
        this.rover = rover;
        this.camera = camera;
        this.image = image;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoverManifest getRover() {
        return rover;
    }

    public void setRover(RoverManifest rover) {
        this.rover = rover;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RoverPicture() {
    }
    @NonNull
    @Override
    public String toString() {
        return "RoverPicture [ id= "+this.id+",rover= "+this.rover.toString()+", date="+this.date+", camera= "+this.camera.toString()+", image= "+this.image+" ]";
    }

}
