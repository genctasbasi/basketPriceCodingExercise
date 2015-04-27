package com.escmobile.bjss.controller;

import com.escmobile.bjss.config.Config;
import com.escmobile.bjss.model.RateJSON;
import com.escmobile.bjss.net.APIClient;
import com.escmobile.bjss.net.APICurrencyList;
import com.escmobile.bjss.net.APIRate;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by genctasbasi on 27/04/15.
 */
public class MainController {

    private MainControllerEventListener mainControllerEventListener;

    public void makeGetCurrencyListCall() {

        APIClient client = APIClient.getInstance();
        APICurrencyList currencyList = client.getCurrencyListClient();

        currencyList.getCurrencyList(new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                if (mainControllerEventListener != null) {
                    mainControllerEventListener.onCurrencyListCallSucceed(jsonObject);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mainControllerEventListener != null) {
                    mainControllerEventListener.onCurrencyListCallFailed(error.toString());
                }
            }
        });
    }

    public void makeGetRateCall(final String currencyTo) {

        APIClient client = APIClient.getInstance();
        APIRate currency = client.getRateClient();

        currency.getRate(Config.BASE_CURRENCY_CODE, currencyTo, new Callback<RateJSON>() {
            @Override
            public void success(RateJSON rate, Response response) {
                if (mainControllerEventListener != null) {
                    mainControllerEventListener.onGetRateCallSucceed(currencyTo, rate);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (mainControllerEventListener != null) {
                    mainControllerEventListener.onGetRateCallFailed(error.toString());
                }
            }
        });
    }

    public void setMainControllerEventListener(MainControllerEventListener mainControllerEventListener) {
        this.mainControllerEventListener = mainControllerEventListener;
    }
}
