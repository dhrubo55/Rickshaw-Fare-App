package com.example.dhruboandroid.bashundhara;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mohak on 2/8/17.
 */

public interface getPolyline {

    @GET("json")
    Call<JsonObject> getPolylineData(@Query("origin") String origin, @Query("destination") String destination/*, @Query("mode") String t,@Query("alternatives") boolean val*/, @Query("key") String key);
}
