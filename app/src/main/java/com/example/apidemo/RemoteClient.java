package com.example.apidemo;






import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RemoteClient {
    private static Retrofit retrofit;
    private static NasaRoverApiService service;

    private static final String API_URL = "https://api.nasa.gov/mars-photos/api/v1/";


    private static Retrofit getInstance(){

        if(retrofit == null){


            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(

                            new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

                    )).build();
        }

        return retrofit;
    }


    public static NasaRoverApiService getService(){

        if(service == null){

            service = getInstance().create(NasaRoverApiService.class);
        }

        return service;
    }


}
