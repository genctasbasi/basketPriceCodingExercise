package com.escmobile.bjss.net;

import com.escmobile.bjss.model.CurrencyCodeJSON;
import com.google.gson.JsonObject;

import java.util.Observable;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * Created by genctasbasi on 25/04/15.
 */
public interface APICurrencyList {
    @GET("/currencies.json")
    void getCurrencyList(Callback<JsonObject> jsonObject);
}
