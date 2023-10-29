package com.example.apidemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoverPictureResponse {
    @SerializedName("photos")
    @Expose
    private List<RoverPicture> roverPicture;

    public List<RoverPicture> getRoverPicture(){
        return this.roverPicture;
    }
}
