package com.escmobile.bjss.controller;

import com.escmobile.bjss.model.RateJSON;
import com.google.gson.JsonObject;

/**
 * Created by genctasbasi on 27/04/15.
 */
public interface MainControllerEventListener {

    void onCurrencyListCallSucceed(JsonObject currencyCodeJSON);

    void onCurrencyListCallFailed(String message);

    void onGetRateCallSucceed(String currencyTo, RateJSON currencyJSON);

    void onGetRateCallFailed(String message);
}
