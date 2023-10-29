package com.example.apidemo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NasaRoverApiService {
    @GET("manifests/{roverName}")
    Single<RoverManifestResponse> getManifestForRover(@Path("roverName") String roverName, @Query("api_key") String key);


    @GET("rovers/{roverName}/photos")
    Single<RoverPictureResponse> getRover(@Path("roverName") String roverName, @Query("earth_date") String date, @Query("api_key") String key);

}
