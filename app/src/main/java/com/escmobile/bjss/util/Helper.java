package com.escmobile.bjss.util;

import android.content.Context;

import com.escmobile.bjss.R;
import com.escmobile.bjss.config.Config;

/**
 * Created by genctasbasi on 25/04/15.
 */
public class Helper {

    public static String parseCurrencyCode(String rowText) {

        // check if we got a valid line
        if (rowText == null || rowText.contains(Config.CURRENCY_CODE_SEPERATOR) == false
                || rowText.length() < Config.MIN_CURRENCY_CODE_LENGTH) {
            return null;
        }

        // make some clearance
        rowText = rowText.substring(0, rowText.indexOf(Config.CURRENCY_CODE_SEPERATOR));
        rowText = rowText.replace("\"", "");

        return rowText;
    }

    /**
     * Builds a string to display on the screen
     *
     * @param context
     * @param currencyTo
     * @param rateStr
     * @param totalInBaseCurrency
     * @return
     */
    public static String getBasketDisplay(
            Context context,
            String currencyTo,
            String rateStr,
            float totalInBaseCurrency) {

        float rate;
        try {
            rate = Float.parseFloat(rateStr);
        } catch (Exception e) {
            return null;
        }

        float totalConverted = totalInBaseCurrency * rate;

        StringBuilder sb = new StringBuilder();
        sb.append(context.getString(R.string.total_in_base_currency) + String.format("%.2f", totalInBaseCurrency) + " " + Config.BASE_CURRENCY_CODE);
        sb.append(System.getProperty("line.separator"));

        sb.append("In " + currencyTo + " : " + String.format("%.2f", totalConverted));

        return sb.toString();
    }
}
