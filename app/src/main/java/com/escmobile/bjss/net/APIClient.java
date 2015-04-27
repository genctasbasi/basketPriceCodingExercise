package com.escmobile.bjss.net;

import com.escmobile.bjss.config.Config;

import retrofit.RestAdapter;

/**
 * This class is responsible for setting retrofit adapters
 * <p/>
 * Created by genctasbasi on 25/04/15.
 */
public class APIClient {

    private static APIClient apiClient;

    public static APIClient getInstance() {
        if (apiClient == null) {
            apiClient = new APIClient();
        }

        return apiClient;
    }

    private APIClient() {
    }

    public APIRate getRateClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Config.API_BASE_URL)
                .build();

        return restAdapter.create(APIRate.class);
    }

    public APICurrencyList getCurrencyListClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Config.API_BASE_URL)

                .build();

        return restAdapter.create(APICurrencyList.class);
    }

}
