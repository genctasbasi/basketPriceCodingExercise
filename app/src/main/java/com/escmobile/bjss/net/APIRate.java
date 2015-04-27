package com.escmobile.bjss.net;

import com.escmobile.bjss.config.Config;
import com.escmobile.bjss.model.RateJSON;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by genctasbasi on 25/04/15.
 */
public interface APIRate {

    @GET("/get?apiKey=" + Config.JSON_RATES_API_KEY)
    void getRate(@Query("from") String from, @Query("to") String to, Callback<RateJSON> rateJSON);
}
