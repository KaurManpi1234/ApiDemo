package com.example.apidemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoverManifestResponse {
    @SerializedName("photo_manifest")
    @Expose
    private RoverManifest manifest;

    public RoverManifest getManifest(){
        return this.manifest;
    }
}
