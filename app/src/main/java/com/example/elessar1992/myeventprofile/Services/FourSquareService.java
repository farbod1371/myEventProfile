package com.example.elessar1992.myeventprofile.Services;


import com.example.elessar1992.myeventprofile.model.FoursquareData.Explore;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
/**
 * Created by elessar1992 on 1/23/18.
 */

public interface FourSquareService
{
    @GET("venues/explore/")
    Call<Explore> requestExplore(
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("v") String v,
            @Query("ll") String ll,
            @Query("query") String query,
            @Query("radius") String radius);


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
