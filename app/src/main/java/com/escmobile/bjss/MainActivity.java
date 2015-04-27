package com.escmobile.bjss;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.escmobile.bjss.controller.MainController;
import com.escmobile.bjss.controller.MainControllerEventListener;
import com.escmobile.bjss.model.RateJSON;
import com.escmobile.bjss.model.data.Beans;
import com.escmobile.bjss.model.data.Eggs;
import com.escmobile.bjss.model.data.Milk;
import com.escmobile.bjss.model.data.Peas;
import com.escmobile.bjss.util.Helper;
import com.escmobile.bjss.widget.Stepper;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ActionBarActivity implements MainControllerEventListener {

    Spinner currenciesSpinner;
    View progressBarLayout;
    List<String> currencyList;

    Stepper stepperBeans, stepperMilk, stepperPeas, stepperEggs;
    TextView display;

    MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        progressBarLayout.setVisibility(View.VISIBLE);
    }

    private void initialize() {

        controller = new MainController();
        controller.setMainControllerEventListener(this);

        // get the currency list first
        controller.makeGetCurrencyListCall();

        display = (TextView) findViewById(R.id.display);

        progressBarLayout = findViewById(R.id.progress_bar_layout);

        stepperBeans = (Stepper) findViewById(R.id.stepper_beans);
        stepperBeans.setDescription(getString(R.string.beans));

        stepperMilk = (Stepper) findViewById(R.id.stepper_milk);
        stepperMilk.setDescription(getString(R.string.milk));

        stepperPeas = (Stepper) findViewById(R.id.stepper_peas);
        stepperPeas.setDescription(getString(R.string.peas));

        stepperEggs = (Stepper) findViewById(R.id.stepper_eggs);
        stepperEggs.setDescription(getString(R.string.eggs));

        currenciesSpinner = (Spinner) findViewById(R.id.currencies_spinner);

        ((Button) findViewById(R.id.button_checkout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout();
            }
        });
    }

    private void checkout() {

        // check if we got something
        if (currenciesSpinner.getSelectedItem() == null || currenciesSpinner.getSelectedItem().toString() == null) {
            handleException(getString(R.string.message_no_currency_list_found));
        }

        final String currencyTo = Helper.parseCurrencyCode(currenciesSpinner.getSelectedItem().toString());

        if (currencyTo == null) {
            handleException(getString(R.string.message_no_currency_list_found));
            return;
        }

        controller.makeGetRateCall(currencyTo);
    }

    /**
     * TODO: Check if cannot be moved out of here
     *
     * @param currencyTo
     * @param rateStr
     */
    private void displayTotalAmount(String currencyTo, String rateStr) {

        String displayString = Helper.getBasketDisplay(this, currencyTo, rateStr, calculateBasketInBaseCurrency());

        if (displayString == null) {
            handleException(getString(R.string.message_cannot_parse_rate));
            return;
        }

        display.setText(displayString);
    }

    private float calculateBasketInBaseCurrency() {
        return
                stepperBeans.getValue() * Beans.BASE_PRICE +
                        stepperEggs.getValue() * Eggs.BASE_PRICE +
                        stepperPeas.getValue() * Peas.BASE_PRICE +
                        stepperMilk.getValue() * Milk.BASE_PRICE;
    }

    private void setCurrencyListAdapter(String list) {

        list = list.replace("{", "").replace("}", "");
        currencyList = Arrays.asList(list.split(","));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencyList);
        currenciesSpinner.setAdapter(adapter);
    }

    @Override
    public void onCurrencyListCallSucceed(JsonObject currencyCodeJSON) {
        progressBarLayout.setVisibility(View.GONE);
        setCurrencyListAdapter(currencyCodeJSON.toString());
    }

    @Override
    public void onCurrencyListCallFailed(String message) {
        handleException(getString(R.string.message_api_call_failed).concat(":").concat(message));
    }

    @Override
    public void onGetRateCallSucceed(String currencyTo, RateJSON currencyJSON) {
        displayTotalAmount(currencyTo, currencyJSON.getRate());
    }

    @Override
    public void onGetRateCallFailed(String message) {
        handleException(getString(R.string.message_api_call_failed).concat(":").concat(message));
    }

    private void handleException(String message) {
        // TODO: Add  proper failure handler & LOG!
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
        progressBarLayout.setVisibility(View.GONE);
    }
}